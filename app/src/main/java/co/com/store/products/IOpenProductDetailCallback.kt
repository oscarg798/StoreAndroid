package co.com.store.products

import co.com.core.use_cases.Product

/**
 * Created by oscarg798 on 1/12/18.
 */
interface IOpenProductDetailCallback {

    fun open(product: Product)
}