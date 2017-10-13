package co.com.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

/**
 * Created by oscarg798 on 10/13/17.
 */

open class LocalStorage private constructor() {


    private var mSharedPreference: SharedPreferences? = null


    private object HOLDER {
        val INSTANCE: LocalStorage = LocalStorage()
    }

    fun create(application: Application){
        mSharedPreference = application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun storeData(key:String, param:String){
        mSharedPreference?.edit()?.putString(key, param)?.apply()
    }

    fun getData(key: String):String?{
        return mSharedPreference?.getString(key, null)
    }

    companion object {
        val instance by lazy {
            HOLDER.INSTANCE
        }
    }
}
