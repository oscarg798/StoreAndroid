package co.com.store.shopping_cart

import co.com.core.Product
import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 10/24/17.
 */
interface IShoppingCartFragmentView : IBaseView {

    fun showProductsOnShoppingCart(products: ArrayList<Pair<Product, Int>>)

    fun showProgressBar()

    fun hideProgressBar()

    fun updateProductQuantity(productUuid:String, quantity:Int)

    fun changeTotal(total:String)
}