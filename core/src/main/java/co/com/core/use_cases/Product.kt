package co.com.core.use_cases

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by oscarg798 on 10/11/17.
 */
data class Product(val mUuid: String,
                   val mName: String,
                   val mDescription: String,
                   val mImages: List<String>?,
                   val mPrice: String,
                   val mCategory: String,
                   val mStore: String) : Parcelable {


    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.createStringArrayList(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(mUuid)
        writeString(mName)
        writeString(mDescription)
        writeStringList(mImages)
        writeString(mPrice)
        writeString(mCategory)
        writeString(mStore)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Product> = object : Parcelable.Creator<Product> {
            override fun createFromParcel(source: Parcel): Product = Product(source)
            override fun newArray(size: Int): Array<Product?> = arrayOfNulls(size)
        }
    }
}