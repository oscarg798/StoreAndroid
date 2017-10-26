package co.com.core.use_cases.location

import co.com.core.Location
import co.com.data.entities.DBLocation
import co.com.data.network.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/26/17.
 */
class RemoveLocationFromFavoriteUseCase(mSubscribeOnScheduler: Scheduler,
                                        mObserverOnScheduler: Scheduler) :
        SingleUseCase<Location, String>(mSubscribeOnScheduler, mObserverOnScheduler) {
    override fun buildUseCase(params: String): Single<Location> {
        return Single.fromObservable(RepositoryFactory.instance.
                mSessionRepository.removeLocationFromFavorite(params))
                .map { apiLocation ->
                    RepositoryFactory.instance.mSessionRepository.
                            insertLocation(DBLocation(apiLocation.uuid, apiLocation.lat,
                                    apiLocation.lng,
                                    apiLocation.user, apiLocation.address,
                                    apiLocation.name, apiLocation.favorite,
                                    apiLocation.indications))

                    Location(apiLocation.uuid, apiLocation.lat, apiLocation.lng,
                            apiLocation.user, apiLocation.address, apiLocation.name,
                            apiLocation.favorite, apiLocation.indications)

                }
    }
}