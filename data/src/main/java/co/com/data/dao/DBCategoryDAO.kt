package co.com.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import co.com.data.entities.DBCategory

/**
 * Created by oscarg798 on 10/18/17.
 */
@Dao
interface DBCategoryDAO{

    @Query("select * from category")
    fun getCategories(): List<DBCategory>

    @Query("select * from category where name=:name")
    fun getCategoryByName(name: String): DBCategory

    @Query("select * from category where uuid=:uuid")
    fun getCategoryById(uuid: String): DBCategory

    @Insert(onConflict = IGNORE)
    fun insertCategory(category: DBCategory)

    @Update
    fun updateCategory(category: DBCategory)
}