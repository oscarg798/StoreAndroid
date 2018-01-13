package co.com.store.dashboard

import android.app.Fragment
import android.os.Bundle
import android.view.MenuItem
import co.com.core.ShoppingCart
import co.com.core.use_cases.Product
import co.com.store.R
import co.com.store.categories.CategoriesFragment
import co.com.store.products.ProductsFragment
import co.com.store.profile.UserProfileFragment
import co.com.store.shopping_cart.ShoppingCartFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/11/17.
 */
class DashboardPresenter : IDashboardActivityPresenter {


    private var mView: IDashboardView? = null


    override fun bind(view: IDashboardView) {
        mView = view
    }

    override fun onCreate(bundle: Bundle?) {
        mView?.initComponents()
        ShoppingCart.instance.mTotalItemsInCartObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<Int>() {
                    override fun onNext(t: Int) {
                        mView?.setShoppingCartBadge(t)
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        mView?.setInitialFragment()
        mView?.setShoppingCartBadge(ShoppingCart.instance.getTotalItemInCartInitialValue())


    }




    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                mView?.changeFragment(CategoriesFragment.newInstance(), null)
                true
            }
            R.id.navigation_profile -> {
                mView?.changeFragment(UserProfileFragment.newInstance(), null)
                true
            }
            R.id.navigation_cart -> {
                mView?.changeFragment(ShoppingCartFragment.newInstance(), null)
                true
            }
            else -> true
        }

    }

    override fun changeFragmentCallback(fragment: Fragment) {
        mView?.changeFragment(fragment, null)
    }
}