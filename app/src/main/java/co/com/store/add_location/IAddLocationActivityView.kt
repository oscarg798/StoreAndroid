package co.com.store.add_location

import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 10/26/17.
 */
interface IAddLocationActivityView : IBaseView {

    fun showProgressBar()

    fun hideProgressBar()

    fun setAddress(address:String)

    fun setAddressName(addressName:String)

    fun onAddressAdded()
}