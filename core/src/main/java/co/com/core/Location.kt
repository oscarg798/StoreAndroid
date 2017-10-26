package co.com.core

/**
 * Created by oscarg798 on 10/19/17.
 */
data class Location(val mUuid: String, val mLat: Double,
                    val mLng: Double, val mUser: String,
                    val mAddress: String,
                    val mName: String,
                    var mFavorite: Boolean,
                    val mIndications: String?)