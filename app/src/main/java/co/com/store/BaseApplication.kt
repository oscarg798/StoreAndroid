package co.com.store

import android.app.Application
import co.com.data.LocalStorage

/**
 * Created by oscarg798 on 10/13/17.
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalStorage.instance.create(this)
    }
}