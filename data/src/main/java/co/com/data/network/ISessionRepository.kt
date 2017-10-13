package co.com.data.network

import co.com.data.APIUser
import co.com.data.LoginRequest
import io.reactivex.Observable

/**
 * Created by oscarg798 on 10/13/17.
 */
interface ISessionRepository {
    fun login(loginRequest: LoginRequest):Observable<APIUser>
}