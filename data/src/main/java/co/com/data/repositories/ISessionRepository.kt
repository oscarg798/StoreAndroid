package co.com.data.repositories

import co.com.data.APILocation
import co.com.data.APIUser
import co.com.data.AddAddressRequest
import co.com.data.LoginRequest
import co.com.data.entities.DBLocation
import co.com.data.entities.DBUser
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Path

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

    fun makeLocationFavorite(uuid: String): Observable<APILocation>

    fun removeLocationFromFavorite(uuid: String): Observable<APILocation>

    fun addAddress(addAddressRequest: AddAddressRequest): Observable<APILocation>
}


