package co.com.core

import co.com.core.use_cases.Product
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
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

    val mTotalObservable: Subject<String> = PublishSubject.create()

    fun addOrRemoveProductFromShoppingCart(product: Product, quantity: Int) {
        if (quantity > 0) {
            mShoppingCartProducts.put(product.mUuid, Pair(product, quantity))
        } else if (mShoppingCartProducts.containsKey(product.mUuid)) {
            mShoppingCartProducts.remove(product.mUuid)
        }
        calculteTotal()

    }

    fun calculteTotal() {
        Single.create<Long> { emitter ->
            val total = 0.toLong() + mShoppingCartProducts.values
                    .map {
                        it.first.mPrice.replace(".", "").toLong() *
                                it.second
                    }
                    .sum()
            emitter.onSuccess(total)


        }.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ value ->
                    val format = NumberFormat.getCurrencyInstance(Locale.US)
                    mTotal = "${format.format(value)}."
                    mTotalObservable.onNext(mTotal)
                })
    }

    fun getTotalInitialValue(): String = mTotal


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