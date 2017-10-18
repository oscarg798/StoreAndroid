package co.com.store.login

import android.os.Bundle
import co.com.core.User
import co.com.core.use_cases.LoginUseCase
import co.com.store.dashboard.IBaseView
import com.core.usecases.ISingleUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/13/17.
 */
class LoginActivityPresenter:ILoginActivityPresenter{
    private var mView:ILoginActivityView? = null

    override fun bind(view: IBaseView) {
        mView = view as ILoginActivityView
    }

    override fun onCreate(bundle:Bundle?) {
        mView?.initComponents()
    }

    override fun onDestroy() {

    }

    override fun login(email: String, password: String) {
        mView?.showProgressBar()

        val iterator:ISingleUseCase<User,Pair<String, String>> = LoginUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())

        iterator.execute(Pair(email, password), object:DisposableSingleObserver<User>(){
            override fun onError(e: Throwable) {
                e.printStackTrace()
                mView?.showError(if(e.message!==null) e.message!! else "Error On Login")
                mView?.hideProgressBar()
                this.dispose()
            }

            override fun onSuccess(t: User) {
                mView?.loginSuccess()
                mView?.hideProgressBar()
                this.dispose()
            }

        })

    }
}