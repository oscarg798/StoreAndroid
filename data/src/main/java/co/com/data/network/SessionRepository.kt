package co.com.data.network

import co.com.data.APILocation
import co.com.data.APIUser
import co.com.data.AppDatabase
import co.com.data.LoginRequest
import co.com.data.entities.DBLocation
import co.com.data.entities.DBUser
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oscarg798 on 10/13/17.
 */
class SessionRepository:ISessionRepository{

    @Inject
    lateinit var mSessionRoute:ISessionRoute

    @Inject
    lateinit var mDatabase: AppDatabase

    override fun login(loginRequest: LoginRequest): Observable<APIUser> {
        return mSessionRoute.login(loginRequest)
    }

    override fun saveUser(dbUser: DBUser) {
        mDatabase.apiUserModel().insertUser(dbUser)
    }

    override fun getUserLogged(token:String): DBUser {
        return mDatabase.apiUserModel().getUserLogged(token)
    }

    override fun deleteUser(dbUser: DBUser) {
        return mDatabase.apiUserModel().deleteUser(dbUser)
    }

    override fun insertLocation(dbLocation: DBLocation) {
        mDatabase.apiLocationModel().insertLocation(dbLocation)
    }

    override fun getUserLocationsFromDB(userUuid: String): List<DBLocation> {
        return mDatabase.apiLocationModel().getLocations(userUuid)
    }

    override fun getUserLocations(userUuid: String): Observable<List<APILocation>> {
        return mSessionRoute.getUserLocations(userUuid)
    }
}