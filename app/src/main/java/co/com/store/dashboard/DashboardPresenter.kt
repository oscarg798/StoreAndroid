package co.com.store.dashboard

import android.util.Log
import android.view.MenuItem
import co.com.core.Category
import co.com.core.use_cases.Product
import co.com.core.use_cases.categories.GetCategoriesUseCase
import co.com.core.use_cases.product.GetProductsUseCase
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
        getCategories()
    }
    fun getCategories() {
        val iterator: ISingleUseCase<List<Category>, Void?> = GetCategoriesUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())

        iterator.execute(null, object : DisposableSingleObserver<List<Category>>() {
            override fun onSuccess(t: List<Category>) {
                t.forEach {
                    Log.d("Category", it.mName) }

                getProducts()
                this.dispose()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                this.dispose()
            }

        })

    }

    fun getProducts(){
        val iterator:ISingleUseCase<List<Product>,Void?> = GetProductsUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())
        iterator.execute(null, object : DisposableSingleObserver<List<Product>>() {
            override fun onSuccess(t: List<Product>) {
                t.forEach {
                    Log.d("Product", it.mName) }

                this.dispose()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                this.dispose()
            }

        })

    }
    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}