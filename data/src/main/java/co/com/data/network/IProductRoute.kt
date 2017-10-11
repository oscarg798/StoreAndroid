package co.com.data.network

import co.com.data.APIProduct
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IProductRoute{

    @GET("product")
    @Headers("@:auth")
    fun getProducts():Observable<List<APIProduct>>
}