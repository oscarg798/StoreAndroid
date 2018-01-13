package co.com.store.dashboard

import android.app.Fragment
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import co.com.core.use_cases.Product
import co.com.store.BadgedDrawable
import co.com.store.R
import co.com.store.products.ProductCallbacks
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), IDashboardView {

    private val mPresenter: IDashboardActivityPresenter = DashboardPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mPresenter.bind(this)
        mPresenter.onCreate(null)

    }

    override fun initComponents() {
        mNavigation?.setOnNavigationItemSelectedListener(mPresenter)
    }

    override fun setInitialFragment() {
        mNavigation?.menu?.let {
            val menuItem = mNavigation.menu.findItem(R.id.navigation_home)
            mPresenter.onNavigationItemSelected(menuItem)
        }

    }

    private fun setBadget(count: Int, icon: LayerDrawable) {
        var badgeDrawable: BadgedDrawable?
        val reuce = icon.findDrawableByLayerId(R.id.ic_badge)
        badgeDrawable = if (reuce !== null && reuce is BadgedDrawable) {
            reuce
        } else {
            BadgedDrawable(this)
        }

        badgeDrawable.setCount(count.toString())
        icon.mutate()
        icon.setDrawableByLayerId(R.id.ic_badge, badgeDrawable)
        icon.invalidateSelf()


    }

    override fun setShoppingCartBadge(count: Int) {
        mNavigation?.let {
            val menuItem = mNavigation.menu.findItem(R.id.navigation_cart)
            menuItem?.let {
                setBadget(count, menuItem.icon as LayerDrawable)
                menuItem.isEnabled = true
            }
        }
    }


    override fun changeFragment(fragment: Fragment, tag: String?) {
        fragmentManager.beginTransaction()
                .replace(R.id.mFMDashboard, fragment)
                .addToBackStack(tag)
                .commitAllowingStateLoss()
    }



    override fun changeFragmentCallback(fragment: Fragment) {
        mPresenter.changeFragmentCallback(fragment)
    }
}
