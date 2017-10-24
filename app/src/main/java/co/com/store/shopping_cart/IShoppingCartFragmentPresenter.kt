package co.com.store.shopping_cart

import co.com.store.dashboard.IBasePresenter
import co.com.store.products.ProductCallbacks

/**
 * Created by oscarg798 on 10/24/17.
 */
interface IShoppingCartFragmentPresenter : IBasePresenter,ProductCallbacks {

    fun onViewCreated()
}