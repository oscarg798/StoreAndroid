package co.com.store.select_address

import co.com.store.checkout.ICheckoutCallbacks
import co.com.store.dashboard.IBasePresenter
import co.com.store.location.LocationAdapterCallbacks

/**
 * Created by oscarg798 on 11/2/17.
 */
interface ISelectAddressFragmentPresenter : IBasePresenter, LocationAdapterCallbacks {

    fun setCheckoutCallbacks(checkoutCallbacks: ICheckoutCallbacks)
}