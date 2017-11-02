package co.com.store.checkout

import co.com.core.Location
import co.com.core.PaymentMethod

/**
 * Created by oscarg798 on 11/2/17.
 */
interface ICheckoutCallbacks {

    fun onAddressSelected(location: Location)

    fun onPaymentMethodSelected(paymentMethod: PaymentMethod)
}