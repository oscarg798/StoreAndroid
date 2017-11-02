package co.com.store.payment_method

import co.com.core.PaymentMethod
import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 11/2/17.
 */
interface IPaymentMethodFragmentView : IBaseView {

    fun showDefaultPaymentMethod(paymentMethod: PaymentMethod)

    fun showProgressBar()

    fun hideProgressBar()

    fun setTotal(total:String)

    fun removeTextWatcher()

    fun addTextWatcher()
}