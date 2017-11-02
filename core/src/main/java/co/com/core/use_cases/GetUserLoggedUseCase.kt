package co.com.core.use_cases

import co.com.core.User
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/19/17.
 */
class GetUserLoggedUseCase(mSubscribeOnScheduler: Scheduler,
                           mObserverOnScheduler: Scheduler) :
        SingleUseCase<User, String>(mSubscribeOnScheduler, mObserverOnScheduler) {
    override fun buildUseCase(params: String): Single<User> {
       return Single.create { emitter->
           val dbUser = RepositoryFactory.instance.mSessionRepository
                   .getUserLogged(params)

           emitter.onSuccess(User(dbUser.uuid, dbUser.name,
                   dbUser.email, dbUser.userType))
       }
    }
}