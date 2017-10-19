package co.com.data.network

import co.com.data.APILocation
import co.com.data.APIUser
import co.com.data.LoginRequest
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by oscarg798 on 10/13/17.
 */
interface ISessionRoute {

    @POST("session")
    fun login(@Body loginRequest: LoginRequest):Observable<APIUser>

    @GET("location/{user}")
    @Headers("@:auth")
    fun getUserLocations(@Path("user") user:String):Observable<List<APILocation>>
}