package co.com.data.repositories

import co.com.data.APIProduct
import co.com.data.network.IProductRoute
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oscarg798 on 10/11/17.
 */
class ProductRepository : IProductRepository {
    @Inject
    lateinit var mProductRoute: IProductRoute

    override fun getProducts(): Observable<List<APIProduct>> {
        return mProductRoute.getProducts()
    }

    override fun getProductsByCategory(uuid: String): Observable<List<APIProduct>> {
        return mProductRoute.getProductsByCategory(uuid)
    }
}