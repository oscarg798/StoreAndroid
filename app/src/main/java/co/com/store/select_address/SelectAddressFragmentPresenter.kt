package co.com.store.select_address

import android.os.Bundle
import co.com.core.Location
import co.com.core.ShoppingCart
import co.com.core.use_cases.location.GetLocationUseCase
import co.com.data.LocalStorage
import co.com.data.TOKEN_KEY
import co.com.store.checkout.ICheckoutCallbacks
import co.com.store.dashboard.IBaseView
import com.core.usecases.ISingleUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 11/2/17.
 */
class SelectAddressFragmentPresenter : ISelectAddressFragmentPresenter {

    private var mView: ISelectedAddressFragmentView? = null

    private var mCheckoutCallbacks: ICheckoutCallbacks? = null

    override fun bind(view: IBaseView) {
        mView = view as ISelectedAddressFragmentView
    }

    override fun onCreate(bundle: Bundle?) {
        mView?.initComponents()
        getLocations()

    }


    private fun getLocations() {
        val iterator: ISingleUseCase<List<Location>, String> = GetLocationUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())

        iterator.execute(LocalStorage.instance.getData(TOKEN_KEY)!!,
                object : DisposableSingleObserver<List<Location>>() {
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        this.dispose()
                    }

                    override fun onSuccess(t: List<Location>) {
                        val locations = ArrayList(t)
                        val favoriteLocation = locations.filter { it.mFavorite }.take(1)

                        if (favoriteLocation.isNotEmpty()) {
                            mView?.showSuggestedAddress(favoriteLocation[0])
                            locations.remove(favoriteLocation[0])
                            mView?.showAddresses(locations)
                        } else if (locations.isNotEmpty()) {
                            mView?.showSuggestedAddress(locations[0])
                            locations.removeAt(0)
                            mView?.showAddresses(locations)
                        }


                        this.dispose()
                    }

                })


    }

    override fun setCheckoutCallbacks(checkoutCallbacks: ICheckoutCallbacks) {
        mCheckoutCallbacks = checkoutCallbacks
    }

    override fun makeLocationFavorite(uuid: String) {

    }

    override fun removeLocationFromFavorite(uuid: String) {

    }

    override fun onLocationClickListener(location: Location) {
        mCheckoutCallbacks?.onAddressSelected(location)
    }


    override fun onDestroy() {
        mView = null
    }
}