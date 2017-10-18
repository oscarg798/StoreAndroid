package co.com.data.network

import co.com.data.APIProduct
import io.reactivex.Observable

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IProductRepository{

    fun getProducts():Observable<List<APIProduct>>

    fun getProductsByCategory(uuid:String):Observable<List<APIProduct>>
}