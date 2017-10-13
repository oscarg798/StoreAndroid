package co.com.store.dashboard

import android.app.Fragment

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IDashboardView:IBaseView{

    fun changeFragment(fragment: Fragment, tag:String?)
}