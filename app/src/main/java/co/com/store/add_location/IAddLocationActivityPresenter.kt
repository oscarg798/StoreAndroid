package co.com.store.add_location

import android.content.Intent
import co.com.store.dashboard.IBasePresenter
import com.google.android.gms.location.places.Place

/**
 * Created by oscarg798 on 10/26/17.
 */
interface IAddLocationActivityPresenter : IBasePresenter {

    fun addLocation(name: String, address: String,
                    indications: String?)

    fun getDataFromPlace(place:Place)

}