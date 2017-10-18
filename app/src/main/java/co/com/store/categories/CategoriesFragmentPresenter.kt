package co.com.store.categories

import android.os.Bundle
import android.view.View
import co.com.core.Category
import co.com.core.use_cases.categories.GetCategoriesUseCase
import co.com.store.dashboard.IBaseView
import co.com.store.products.ProductsFragment
import co.com.store.utils.FragmentCallback
import com.core.usecases.ISingleUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/13/17.
 */
class CategoriesFragmentPresenter : ICategoryFragmentPresenter {

    private var mView: ICategoryFragmentView? = null

    private var mFragmentCallback: FragmentCallback? = null

    override fun bind(view: IBaseView) {
        mView = view as ICategoryFragmentView
    }

    override fun bindFragmentCallback(callback: FragmentCallback) {
        mFragmentCallback = callback
    }

    override fun onCreate(bundle:Bundle?) {

    }

    override fun onViewCreated() {
        mView?.initComponents()
        getCategories()
    }

    private fun getCategories() {

        mView?.showProgressBar()
        val iterator: ISingleUseCase<List<Category>, Void?> = GetCategoriesUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())

        iterator.execute(null, object : DisposableSingleObserver<List<Category>>() {
            override fun onSuccess(t: List<Category>) {
                mView?.showCategories(ArrayList(t))
                mView?.hideProgressBar()
                this.dispose()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mView?.hideProgressBar()
                this.dispose()
            }

        })

    }


    override fun onCategoryClicked(view: View, category: Category) {
        mFragmentCallback?.changeFragmentCallback(ProductsFragment.newInstance(category.mUuid))
    }


    override fun onRefresh() {
        getCategories()
        mView?.clearCategories()
    }

    override fun onDestroy() {
    }
}

