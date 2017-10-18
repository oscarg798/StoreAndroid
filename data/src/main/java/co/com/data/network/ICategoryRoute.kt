package co.com.data.network

import co.com.data.APICategory
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by oscarg798 on 10/11/17.
 */
interface ICategoryRoute {

    @GET("category")
    fun getCategories(): Observable<List<APICategory>>
}