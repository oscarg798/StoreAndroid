package co.com.store.categories

import co.com.core.Category
import co.com.core.use_cases.Product
import co.com.core.use_cases.categories.GetCategoriesUseCase
import co.com.core.use_cases.product.GetProductsUseCase
import co.com.store.dashboard.IBaseView
import com.core.usecases.ISingleUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/13/17.
 */
class CategoriesFragmentPresenter : ICategoryFragmentPresenter {

    private var mView: ICategoryFragmentView? = null

    private var mCategoriesList: List<Category>? = null


    override fun bind(view: IBaseView) {
        mView = view as ICategoryFragmentView
    }

    override fun onCreate() {

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
                mCategoriesList = t
                getProducts()
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

    private fun getProducts() {
        mView?.showProgressBar()
        val iterator: ISingleUseCase<List<Product>, Void?> = GetProductsUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())
        iterator.execute(null, object : DisposableSingleObserver<List<Product>>() {
            override fun onSuccess(t: List<Product>) {
                mergeCategoriesAndProducts(t)
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

    private fun mergeCategoriesAndProducts(products: List<Product>) {

        mView?.showProgressBar()
        Single.create<ArrayList<ViewCategory>> { emitter ->

            val hashProducts = HashMap<String, ArrayList<Product>>()
            products.forEach { product ->
                if (hashProducts[product.mCategory] === null) {
                    hashProducts.put(product.mCategory, ArrayList())
                }
                val productsOnMap = hashProducts[product.mCategory]
                productsOnMap?.let {
                    productsOnMap.add(product)
                    hashProducts.put(product.mCategory, productsOnMap)
                }
            }

            val mViewCategories = ArrayList<ViewCategory>()

            mCategoriesList?.let {
                mCategoriesList!!.forEach {
                    val categoryProducts: ArrayList<Product> =
                            if (hashProducts.containsKey(it.mUuid)) hashProducts[it.mUuid]!!
                            else ArrayList()
                    mViewCategories.add(ViewCategory(it.mName, categoryProducts))
                }
            }
            emitter.onSuccess(mViewCategories)

        }.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<ArrayList<ViewCategory>>() {
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        mView?.hideProgressBar()
                    }

                    override fun onSuccess(t: ArrayList<ViewCategory>) {
                        mView?.showCategoriesAndProducts(t)
                        mView?.hideProgressBar()


                    }

                })
    }

    override fun onRefresh() {
        getCategories()
        mView?.clearCategories()
    }

    override fun onDestroy() {
    }
}

