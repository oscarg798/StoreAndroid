package co.com.store

import android.app.Application
import co.com.data.LocalStorage
import co.com.data.repositories.RepositoryFactory

/**
 * Created by oscarg798 on 10/13/17.
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RepositoryFactory.instance.injectContextAndInit(this)
        LocalStorage.instance.create(this)
    }


}