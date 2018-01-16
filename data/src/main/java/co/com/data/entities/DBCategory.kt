package co.com.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oscarg798 on 10/18/17.
 */
@Entity(tableName = "category")
data class DBCategory(@PrimaryKey val uuid: String, val name: String, val avatar: String?,
                      val description: String?,
                      val store: String)