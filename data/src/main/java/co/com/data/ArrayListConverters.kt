package co.com.data

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by oscarg798 on 10/18/17.
 */
@TypeConverter
fun stringToArrayListString(value: String?): ArrayList<String>? {

    if (value !== null) {
        return Gson().fromJson(value, object : TypeToken<ArrayList<String>>() {}.type)
    }
    return null
}

@TypeConverter
fun arrayListStringToString(value: ArrayList<String>?): String? {
    if (value !== null) {
        return Gson().toJson(value)
    }
    return null
}