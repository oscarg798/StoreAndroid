package co.com.core.use_cases

import co.com.core.User
import co.com.data.LocalStorage
import co.com.data.LoginRequest
import co.com.data.TOKEN_KEY
import co.com.data.entities.DBUser
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/13/17.
 */
class LoginUseCase(mSubscribeOnScheduler: Scheduler,
                   mObserverOnScheduler: Scheduler) :
        SingleUseCase<User, Pair<String, String>>(mSubscribeOnScheduler, mObserverOnScheduler) {


    override fun buildUseCase(params: Pair<String, String>): Single<User> {
        return Single.fromObservable(RepositoryFactory.instance
                .mSessionRepository.login(LoginRequest(params.first, params.second)))
                .map {

                    RepositoryFactory.instance.mSessionRepository
                            .saveUser(DBUser(it.uuid, it.name, it.email, it.userType, it.token))

                    LocalStorage.instance.storeData(TOKEN_KEY, it.token)

                    User(it.uuid, it.name, it.email, it.userType)
                }
    }
}