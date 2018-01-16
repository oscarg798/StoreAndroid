package co.com.store.checkout

import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 1/15/18.
 */
interface ICheckoutActivityView : IBaseView {

    fun showFavoriteLocation(address: String)

    fun showTotalInCart(total: String)

    fun showDeliverCost(deliverCost: String)

    fun showBTNPlaceOrderText(text: String)

}