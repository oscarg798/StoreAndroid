package co.com.data.network

import co.com.data.APILocation
import co.com.data.APIUser
import co.com.data.LoginRequest
import co.com.data.entities.DBLocation
import co.com.data.entities.DBUser
import io.reactivex.Observable

/**
 * Created by oscarg798 on 10/13/17.
 */
interface ISessionRepository {

    fun login(loginRequest: LoginRequest): Observable<APIUser>

    fun saveUser(dbUser: DBUser)

    fun getUserLogged(token: String): DBUser

    fun deleteUser(dbUser: DBUser)

    fun insertLocation(dbLocation: DBLocation)

    fun getUserLocationsFromDB(userUuid: String): List<DBLocation>

    fun getUserLocations(userUuid: String): Observable<List<APILocation>>
}