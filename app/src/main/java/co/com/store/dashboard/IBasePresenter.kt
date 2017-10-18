package co.com.store.dashboard

import android.os.Bundle

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IBasePresenter{

    fun bind(view:IBaseView)

    fun onCreate(bundle:Bundle?)

    fun onDestroy()

}