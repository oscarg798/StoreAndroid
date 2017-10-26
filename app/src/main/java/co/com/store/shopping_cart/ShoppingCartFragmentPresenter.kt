package co.com.store.shopping_cart

import android.os.Bundle
import co.com.core.ShoppingCart
import co.com.core.use_cases.Product
import co.com.store.dashboard.IBaseView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/24/17.
 */
class ShoppingCartFragmentPresenter : IShoppingCartFragmentPresenter {

    private var mView: IShoppingCartFragmentView? = null

    override fun bind(view: IBaseView) {
        mView = view as IShoppingCartFragmentView
    }

    override fun onCreate(bundle: Bundle?) {

    }

    override fun onViewCreated() {
        mView?.initComponents()
        subscribeToTalChanges()
        mView?.showProductsOnShoppingCart(ArrayList(ShoppingCart.instance
                .getProductsFromShoppingCart()))
        mView?.changeTotal(ShoppingCart.instance.getTotalInitialValue())

    }

    private fun subscribeToTalChanges(){
        ShoppingCart.instance.mTotalObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({total->
                    mView?.changeTotal(total)
                })

    }


    override fun onDestroy() {
        mView = null
    }

    override fun addOrRemoveProductFromShoppingCart(product: Product, quantity: Int) {
        ShoppingCart.instance.addOrRemoveProductFromShoppingCart(product, quantity)
        mView?.updateProductQuantity(product.mUuid, ShoppingCart.instance
                .getQuantityForProduct(product.mUuid))
    }
}