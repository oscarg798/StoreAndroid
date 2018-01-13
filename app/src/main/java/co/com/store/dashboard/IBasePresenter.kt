package co.com.store.dashboard

import android.os.Bundle

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IBasePresenter<in T : IBaseView> {

    fun bind(view: T)

    fun onCreate(bundle: Bundle?)

    fun onDestroy()

}