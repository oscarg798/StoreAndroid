package co.com.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oscarg798 on 10/19/17.
 */
@Entity(tableName = "user")
data class DBUser(@PrimaryKey val uuid: String, val name: String,
                  val email: String, val userType: String,
                  val token:String)