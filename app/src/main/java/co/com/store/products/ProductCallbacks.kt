package co.com.store.products

import co.com.core.Product

/**
 * Created by oscarg798 on 10/23/17.
 */
interface ProductCallbacks {

    fun addOrRemoveProductFromShoppingCart(product: Product, quantity: Int)
}

