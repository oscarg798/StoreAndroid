package co.com.store.checkout

import android.os.Bundle
import co.com.core.Location
import co.com.core.ShoppingCart
import co.com.core.use_cases.location.GetFavoriteLocationUseCase
import co.com.data.LocalStorage
import co.com.data.TOKEN_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat
import java.util.*

/**
 * Created by oscarg798 on 1/15/18.
 */
class CheckoutActivityPresenter : ICheckoutActivityPresenter {

    private var mView: ICheckoutActivityView? = null
    override fun bind(view: ICheckoutActivityView) {
        mView = view
        mView?.initComponents()
        mView?.showTotalInCart(ShoppingCart.instance.getTotalInitialValue())
        mView?.showDeliverCost(NumberFormat.getCurrencyInstance(Locale.US).format(2000))

        mView?.showBTNPlaceOrderText("Place order ${NumberFormat.getCurrencyInstance(Locale.US)
                .format(NumberFormat.getCurrencyInstance(Locale.US)
                        .parse(ShoppingCart.instance.getTotalInitialValue()).toInt() + 2000)}")
        getFavoriteAddress()

    }

    private fun getFavoriteAddress() {
        GetFavoriteLocationUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())
                .execute(LocalStorage.instance.getData(TOKEN_KEY)!!, object : DisposableSingleObserver<Location>() {
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        this.dispose()

                    }

                    override fun onSuccess(t: Location) {
                        mView?.showFavoriteLocation(t.mAddress)
                        this.dispose()
                    }
                })

    }

    override fun onCreate(bundle: Bundle?) {
    }

    override fun onDestroy() {
    }


}