package co.com.store.products

import co.com.core.Product
import co.com.store.dashboard.IBaseView
import co.com.store.products.detail.ProductDetailDialogFragment

/**
 * Created by oscarg798 on 10/18/17.
 */
interface IProductFragmentView : IBaseView {

    fun showProducts(products: List<Product>)

    fun showProgressBar()

    fun hideProgressBar()

    fun showProductDetail(productDetailDialogFragment: ProductDetailDialogFragment)

    fun notifyDatasetHasChange()
}