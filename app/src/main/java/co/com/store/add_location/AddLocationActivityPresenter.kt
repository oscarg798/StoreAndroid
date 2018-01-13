package co.com.store.add_location

import android.os.Bundle
import co.com.core.Location
import co.com.core.use_cases.location.AddAddressUseCase
import co.com.data.AddAddressRequest
import co.com.store.dashboard.IBaseView
import com.core.usecases.ISingleUseCase
import com.google.android.gms.location.places.Place
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/26/17.
 */
class AddLocationActivityPresenter : IAddLocationActivityPresenter {

    private var mView: IAddLocationActivityView? = null

    private var mLatLng: LatLng? = null

    override fun bind(view: IAddLocationActivityView) {
        mView = view
    }

    override fun onCreate(bundle: Bundle?) {
        mView?.initComponents()
    }

    override fun addLocation(name: String, address: String, indications: String?) {

        mLatLng?.let {
            mView?.showProgressBar()
            val iterator: ISingleUseCase<Location, AddAddressRequest> =
                    AddAddressUseCase(Schedulers.io(), AndroidSchedulers.mainThread())

            val addAddressRequest = AddAddressRequest(address, name, indications, "",
                    mLatLng!!.latitude, mLatLng!!.longitude)

            iterator.execute(addAddressRequest, object : DisposableSingleObserver<Location>() {
                override fun onError(e: Throwable) {
                    mView?.hideProgressBar()
                    this.dispose()
                }

                override fun onSuccess(t: Location) {
                    mView?.onAddressAdded()
                    mView?.hideProgressBar()
                    this.dispose()
                }

            })

        }

    }

    override fun onDestroy() {
        mView = null
    }

    override fun getDataFromPlace(place: Place) {
        if (place.address !== null) {
            mView?.setAddress(place.address.toString())
        }

        if (place.name !== null) {
            mView?.setAddressName(place.name.toString())
        }

        mLatLng = place.latLng
    }


}