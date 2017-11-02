package co.com.core.use_cases

import co.com.data.LocalStorage
import co.com.data.TOKEN_KEY
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/19/17.
 */
class LogOutUseCase(mSubscribeOnScheduler: Scheduler,
                    mObserverOnScheduler: Scheduler) :
        SingleUseCase<Boolean, Void?>(mSubscribeOnScheduler, mObserverOnScheduler) {

    override fun buildUseCase(params: Void?): Single<Boolean> {
        return Single.create { emitter ->
            val token = LocalStorage.instance.getData(TOKEN_KEY)
            if(token!==null){
                val dbUser = RepositoryFactory.instance.mSessionRepository
                        .getUserLogged(token)
                RepositoryFactory.instance.mSessionRepository.deleteUser(dbUser)
                LocalStorage.instance.removeData(TOKEN_KEY)
                emitter.onSuccess(true)
            }else{
                emitter.onError(NullPointerException())
            }
        }
    }
}