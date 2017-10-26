package co.com.data

/**
 * Created by oscarg798 on 10/26/17.
 */
data class AddAddressRequest(val address: String,
                             val name: String,
                             val indications: String?,
                             var user: String,
                             val lat: Double,
                             val lng: Double)