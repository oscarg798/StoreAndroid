package co.com.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import co.com.data.dao.DBCategoryDAO
import co.com.data.entities.DBCategory

/**
 * Created by oscarg798 on 10/11/17.
 */
@Database(entities = arrayOf( DBCategory::class), version = 1)
abstract class AppDatabase:RoomDatabase(){

    abstract fun apiCategoryModel(): DBCategoryDAO
}