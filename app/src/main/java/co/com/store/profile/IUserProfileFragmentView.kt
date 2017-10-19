package co.com.store.profile

import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 10/19/17.
 */
interface IUserProfileFragmentView:IBaseView{

    fun hideUserDetails()

    fun showUserDetails()

    fun setName(name:String)

    fun setEmail(email:String)

    fun showProgressBar()

    fun hideProgressBar()

}