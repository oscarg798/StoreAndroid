package co.com.store.login

import co.com.store.dashboard.IBasePresenter

/**
 * Created by oscarg798 on 10/13/17.
 */
interface ILoginActivityPresenter : IBasePresenter {

    fun login(email:String, password:String)
}