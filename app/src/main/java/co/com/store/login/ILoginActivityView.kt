package co.com.store.login

import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 10/13/17.
 */
interface ILoginActivityView : IBaseView {

    fun showError(message: String)

    fun loginSuccess()

    fun showProgressBar()

    fun hideProgressBar()
}