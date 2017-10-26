package co.com.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Created by oscarg798 on 10/19/17.
 */
@Entity(tableName = "location",
        indices = arrayOf(Index("user")),
        foreignKeys = arrayOf(ForeignKey(entity = DBUser::class,
                parentColumns = arrayOf("uuid"),
                childColumns = arrayOf("user"))))
data class DBLocation(@PrimaryKey val uuid: String, val lat: Double,
                      val lng: Double, val user: String,
                      val address: String,
                      val name: String,
                      val favorite: Boolean,
                      val indications: String?)