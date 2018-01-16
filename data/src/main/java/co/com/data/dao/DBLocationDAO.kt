package co.com.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import co.com.data.entities.DBLocation

/**
 * Created by oscarg798 on 10/19/17.
 */
@Dao
interface DBLocationDAO {

    @Query("SELECT * from location where location.user=:user")
    fun getLocations(user: String): List<DBLocation>

    @Query("SELECT * FROM location where location.user=:user AND favorite=1")
    fun getFavoriteLocation(user: String): List<DBLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(dbLocation: DBLocation)
}