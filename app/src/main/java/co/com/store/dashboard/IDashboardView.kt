package co.com.store.dashboard

import android.app.Fragment
import co.com.store.utils.FragmentCallback

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IDashboardView : IBaseView,
        FragmentCallback {

    fun changeFragment(fragment: Fragment, tag: String?)

    fun setShoppingCartBadge(count:Int)

    fun setInitialFragment()
}
