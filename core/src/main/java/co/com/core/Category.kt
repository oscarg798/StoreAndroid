package co.com.core

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by oscarg798 on 10/11/17.
 */
data class Category(val mName: String,
                    val mAvatar: String?,
                    val mUuid: String) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(mName)
        writeString(mAvatar)
        writeString(mUuid)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Category> = object : Parcelable.Creator<Category> {
            override fun createFromParcel(source: Parcel): Category = Category(source)
            override fun newArray(size: Int): Array<Category?> = arrayOfNulls(size)
        }
    }
}