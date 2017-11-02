package co.com.core

import co.com.core.use_cases.Product
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.text.NumberFormat
import java.util.*

/**
 * Created by oscarg798 on 10/24/17.
 */
class ShoppingCart(private val mShoppingCartProducts: HashMap<String, Pair<Product, Int>>) {

    private var mTotal: String = "$0"

    private var mTotalItemsInCart = 0

    val mTotalObservable: Subject<String> = PublishSubject.create()

    val mTotalItemsInCartObservable: Subject<Int> = PublishSubject.create()



    fun addOrRemoveProductFromShoppingCart(product: Product, quantity: Int) {
        if (quantity > 0) {
            mShoppingCartProducts.put(product.mUuid, Pair(product, quantity))
        } else if (mShoppingCartProducts.containsKey(product.mUuid)) {
            mShoppingCartProducts.remove(product.mUuid)
        }
        calculteTotal()

    }

    private fun calculteTotal() {
        Single.create<Pair<Int, Long>> { emitter ->
            val total = 0.toLong() + mShoppingCartProducts.values
                    .map {
                        it.first.mPrice.replace(".", "").toLong() *
                                it.second
                    }
                    .sum()

            val quantity = 0 + mShoppingCartProducts.values
                    .map { it.second }
                    .sum()

            emitter.onSuccess(Pair(quantity, total))


        }.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ value ->
                    val format = NumberFormat.getCurrencyInstance(Locale.US)
                    mTotal = format.format(value.second)
                    mTotalItemsInCart = value.first
                    mTotalObservable.onNext(mTotal)
                    mTotalItemsInCartObservable.onNext(value.first)
                })
    }

    fun getTotalInitialValue(): String = mTotal

    fun getTotalItemInCartInitialValue(): Int = mTotalItemsInCart

    fun getQuantityForProduct(productUuid: String): Int {
        if (mShoppingCartProducts.containsKey(productUuid)) {
            return mShoppingCartProducts[productUuid]!!.second
        }
        return 0
    }

    fun getProductsFromShoppingCart(): List<Pair<Product, Int>> {
        return ArrayList(mShoppingCartProducts.values)
    }

    private object Holder {
        val INSTANCE: ShoppingCart = ShoppingCart(HashMap())
    }

    companion object {
        val instance by lazy {
            Holder.INSTANCE
        }
    }

}