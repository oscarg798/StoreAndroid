package co.com.data.network

import co.com.data.APIUser
import co.com.data.LoginRequest
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oscarg798 on 10/13/17.
 */
class SessionRepository:ISessionRepository{

    @Inject
    lateinit var mSessionRoute:ISessionRoute

    override fun login(loginRequest: LoginRequest): Observable<APIUser> {
        return mSessionRoute.login(loginRequest)
    }
}