package co.com.data.network

import co.com.data.APIProduct
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IProductRoute{

    @GET("product")
    fun getProducts():Observable<List<APIProduct>>

    @GET("product/category/{uuid}")
    fun getProductsByCategory(@Path("uuid") uuid:String):Observable<List<APIProduct>>

}