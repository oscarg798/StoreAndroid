package co.com.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import co.com.data.dao.DBCategoryDAO
import co.com.data.dao.DBLocationDAO
import co.com.data.dao.DBUserDAO
import co.com.data.entities.DBCategory
import co.com.data.entities.DBLocation
import co.com.data.entities.DBUser

/**
 * Created by oscarg798 on 10/11/17.
 */
@Database(entities = arrayOf(DBCategory::class, DBUser::class, DBLocation::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun apiCategoryModel(): DBCategoryDAO

    abstract fun apiUserModel(): DBUserDAO

    abstract fun apiLocationModel():DBLocationDAO
}