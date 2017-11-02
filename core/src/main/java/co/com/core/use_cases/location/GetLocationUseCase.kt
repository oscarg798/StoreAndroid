package co.com.core.use_cases.location

import co.com.core.Location
import co.com.data.entities.DBLocation
import co.com.data.entities.DBUser
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/19/17.
 */
class GetLocationUseCase(mSubscribeOnScheduler: Scheduler,
                         mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<Location>, String>(mSubscribeOnScheduler, mObserverOnScheduler) {
    override fun buildUseCase(params: String): Single<List<Location>> {
        return Single.create<DBUser> { emitter ->
            val dbUser = RepositoryFactory.instance.mSessionRepository
                    .getUserLogged(params)


            emitter.onSuccess(dbUser)
        }.flatMap { dbUser ->
            Single.fromObservable(RepositoryFactory.instance.mSessionRepository.
                    getUserLocations(dbUser.uuid))
        }.map { apiLocations ->

            apiLocations.forEach { apiLocation ->
                RepositoryFactory.instance.mSessionRepository.
                        insertLocation(DBLocation(apiLocation.uuid, apiLocation.lat, apiLocation.lng,
                                apiLocation.user, apiLocation.address,
                                apiLocation.name, apiLocation.favorite, apiLocation.indications))
            }
            apiLocations
                    .map { apiLocation ->
                        Location(apiLocation.uuid, apiLocation.lat, apiLocation.lng,
                                apiLocation.user, apiLocation.address, apiLocation.name,
                                apiLocation.favorite, apiLocation.indications)

                    }

        }
    }
}