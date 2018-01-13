package co.com.store.profile

import android.os.Bundle
import co.com.core.User
import co.com.core.use_cases.GetUserLoggedUseCase
import co.com.core.use_cases.LogOutUseCase
import co.com.data.LocalStorage
import co.com.data.TOKEN_KEY
import co.com.store.dashboard.IBaseView
import co.com.store.location.LocationFragment
import co.com.store.utils.FragmentCallback
import com.core.usecases.ISingleUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/19/17.
 */
class UserProfileFragmentPresenter : IUserProfileFragmentPresenter {

    private var mView: IUserProfileFragmentView? = null

    private var mFragmentCallback: FragmentCallback? = null

    override fun bind(view: IUserProfileFragmentView) {
        mView = view
    }

    override fun onCreate(bundle: Bundle?) {

    }

    override fun onViewCreated() {
        mView?.initComponents()
        if (LocalStorage.instance.getData(TOKEN_KEY) !== null) {
            getUser()
        } else {
            mView?.hideUserDetails()

        }
    }

    override fun logOut() {

        mView?.showProgressBar()
        val iterator: ISingleUseCase<Boolean, Void?> = LogOutUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())

        iterator.execute(null, object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                mView?.hideUserDetails()
                mView?.hideProgressBar()
                this.dispose()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mView?.hideProgressBar()
                this.dispose()
            }

        })
    }

    private fun getUser() {
        mView?.showProgressBar()
        val iterator: ISingleUseCase<User, String> = GetUserLoggedUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())

        iterator.execute(LocalStorage.instance.getData(TOKEN_KEY)!!, object : DisposableSingleObserver<User>() {
            override fun onSuccess(t: User) {
                mView?.showUserDetails()
                mView?.setName(t.mName)
                mView?.setEmail(t.mEmail)
                mView?.hideProgressBar()
                this.dispose()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mView?.hideUserDetails()
                mView?.hideProgressBar()
                this.dispose()
            }

        })
    }

    override fun bindFragmentCallback(callback: FragmentCallback) {
        mFragmentCallback = callback
    }

    override fun openAddressFragment() {
        mFragmentCallback?.changeFragmentCallback(LocationFragment.newInstance())
    }

    override fun onDestroy() {

    }
}