package co.com.core.use_cases.location

import co.com.core.Location
import co.com.data.AddAddressRequest
import co.com.data.LocalStorage
import co.com.data.TOKEN_KEY
import co.com.data.entities.DBLocation
import co.com.data.entities.DBUser
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/26/17.
 */
class AddAddressUseCase(mSubscribeOnScheduler: Scheduler,
                        mObserverOnScheduler: Scheduler) :
        SingleUseCase<Location, AddAddressRequest>(mSubscribeOnScheduler, mObserverOnScheduler) {
    override fun buildUseCase(params: AddAddressRequest): Single<Location> {
        return Single.create<DBUser> { emitter ->
            val dbUser = RepositoryFactory.instance.mSessionRepository
                    .getUserLogged(LocalStorage.instance.getData(TOKEN_KEY)!!)


            emitter.onSuccess(dbUser)
        }.flatMap { dbUser ->
            params.user = dbUser.uuid
            Single.fromObservable(RepositoryFactory.instance.mSessionRepository.addAddress(params))
        }.map { apiLocation ->
            RepositoryFactory.instance.mSessionRepository.
                    insertLocation(DBLocation(apiLocation.uuid, apiLocation.lat,
                            apiLocation.lng,
                            apiLocation.user, apiLocation.address,
                            apiLocation.name, apiLocation.favorite, apiLocation.indications))

            co.com.core.Location(apiLocation.uuid, apiLocation.lat, apiLocation.lng,
                    apiLocation.user, apiLocation.address, apiLocation.name,
                    apiLocation.favorite, apiLocation.indications)


        }
    }
}