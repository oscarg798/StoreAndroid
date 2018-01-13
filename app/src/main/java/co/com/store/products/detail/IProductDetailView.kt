package co.com.store.products.detail

import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 1/12/18.
 */
interface IProductDetailView : IBaseView {

    fun setProductName(name: String)

    fun setProductImage(image: String)

    fun setProductDescription(description: String)

    fun setProductQuantity(quantity: String)

    fun setBTNAddToCartText(stringResourceId: Int)

    fun setPrice(price: String)


}