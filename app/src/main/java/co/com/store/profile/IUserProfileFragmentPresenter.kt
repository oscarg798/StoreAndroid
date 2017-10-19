package co.com.store.profile

import co.com.store.dashboard.IBasePresenter
import co.com.store.utils.FragmentCallback

/**
 * Created by oscarg798 on 10/19/17.
 */
interface IUserProfileFragmentPresenter:IBasePresenter{

    fun onViewCreated()

    fun logOut()

    fun bindFragmentCallback(callback: FragmentCallback)

    fun openAddressFragment()
}