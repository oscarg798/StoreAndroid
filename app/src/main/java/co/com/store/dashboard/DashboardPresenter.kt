package co.com.store.dashboard

import android.util.Log
import android.view.MenuItem
import co.com.core.Category
import co.com.core.use_cases.Product
import co.com.core.use_cases.categories.GetCategoriesUseCase
import co.com.core.use_cases.product.GetProductsUseCase
import co.com.store.categories.CategoriesFragment
import com.core.usecases.ISingleUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/11/17.
 */
class DashboardPresenter : IDashboardActivityPresenter {


    private var mView: IDashboardView? = null

    override fun bind(view: IBaseView) {
        mView = view as IDashboardView
    }

    override fun onCreate() {
        mView?.changeFragment(CategoriesFragment.newInstance(), null)
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}