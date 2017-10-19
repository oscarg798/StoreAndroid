package co.com.data.dao

import android.arch.persistence.room.*
import co.com.data.entities.DBUser

/**
 * Created by oscarg798 on 10/19/17.
 */
@Dao
interface DBUserDAO {

    @Query("select * from user where token= :token")
    fun getUserLogged(token: String): DBUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(dbUser: DBUser)

    @Delete
    fun deleteUser(dbUser: DBUser)
}