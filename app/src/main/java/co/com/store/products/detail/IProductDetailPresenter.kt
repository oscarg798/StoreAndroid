package co.com.store.products.detail


import co.com.store.dashboard.IBasePresenter
import co.com.store.products.ProductCallbacks

/**
 * Created by oscarg798 on 1/12/18.
 */
interface IProductDetailPresenter : IBasePresenter<IProductDetailView> {


    fun addOrRemoveFromQuantity(add: Boolean)

    fun btnAddToCartPressed()

}