package co.com.store.location

import co.com.core.Location

/**
 * Created by oscarg798 on 10/26/17.
 */
interface LocationAdapterCallbacks {

    fun makeLocationFavorite(uuid: String)

    fun removeLocationFromFavorite(uuid: String)

    fun onLocationClickListener(location: Location)
}