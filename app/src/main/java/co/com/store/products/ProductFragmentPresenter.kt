package co.com.store.products

import android.os.Bundle
import co.com.core.ShoppingCart
import co.com.core.use_cases.Product
import co.com.core.use_cases.product.GetProductsByCategoryUseCase
import co.com.data.CATEGORY_UUID_KEY
import co.com.store.dashboard.IBaseView
import com.core.usecases.ISingleUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/18/17.
 */
class ProductFragmentPresenter : IProductFragmentPresenter {

    private var mView: IProductFragmentView? = null

    private var mCategoryUuid: String? = null

    override fun bind(view: IBaseView) {
        mView = view as IProductFragmentView
    }

    override fun onCreate(bundle: Bundle?) {
        bundle?.let {
            mCategoryUuid = bundle.getString(CATEGORY_UUID_KEY)
        }
    }

    override fun onViewCreated() {
        mView?.initComponents()
    }


    override fun getProducts() {
        mCategoryUuid?.let {
            mView?.showProgressBar()
            val iterator: ISingleUseCase<List<Product>, String> = GetProductsByCategoryUseCase(Schedulers.io(),
                    AndroidSchedulers.mainThread())

            iterator.execute(mCategoryUuid!!, object : DisposableSingleObserver<List<Product>>() {
                override fun onSuccess(t: List<Product>) {
                    mView?.showProducts(t)
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

    }

    override fun addOrRemoveProductFromShoppingCart(product: Product, quantity: Int) {
        ShoppingCart.instance.addOrRemoveProductFromShoppingCart(product, quantity)
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}