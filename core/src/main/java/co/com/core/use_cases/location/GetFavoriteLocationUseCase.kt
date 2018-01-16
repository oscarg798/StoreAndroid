package co.com.core.use_cases.location

import co.com.core.Location
import co.com.data.entities.DBLocation
import co.com.data.entities.DBUser
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 1/15/18.
 */
class GetFavoriteLocationUseCase(mSubscribeOnScheduler: Scheduler,
                                 mObserverOnScheduler: Scheduler) :
        SingleUseCase<Location, String>(mSubscribeOnScheduler, mObserverOnScheduler) {

    override fun buildUseCase(params: String): Single<Location> {
        return Single.create<DBUser> { emitter ->
            val dbUser = RepositoryFactory.instance.mSessionRepository
                    .getUserLogged(params)


            emitter.onSuccess(dbUser)
        }.flatMap { dbUser ->
            Single.create<DBLocation> { emitter ->
                emitter.onSuccess(RepositoryFactory.instance.mSessionRepository.getFavoriteLocation(dbUser.uuid)[0])
            }
        }.flatMap { dbLocation ->
            Single.create<Location> { emitter ->
                emitter.onSuccess(Location(dbLocation.uuid, dbLocation.lat, dbLocation.lng, dbLocation.user,
                        dbLocation.address, dbLocation.name, dbLocation.favorite, dbLocation.indications))
            }

        }


    }
}