package co.com.store.payment_method

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import co.com.core.PaymentMethod
import co.com.core.ShoppingCart
import co.com.core.use_cases.GetPaymentMethodsUseCase
import co.com.store.dashboard.IBaseView
import com.core.usecases.ISingleUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat
import java.util.*

/**
 * Created by oscarg798 on 11/2/17.
 */
class PaymentMethodFragmentPresenter : IPaymentMethodFragmentPresenter {

    private var mView: IPaymentMethodFragmentView? = null


    override fun bind(view: IPaymentMethodFragmentView) {
        mView = view
    }

    private fun getPaymentMethods() {
        mView?.showProgressBar()
        val iterator: ISingleUseCase<ArrayList<PaymentMethod>, Any?> =
                GetPaymentMethodsUseCase(Schedulers.io(), AndroidSchedulers.mainThread())

        iterator.execute(null, object : DisposableSingleObserver<ArrayList<PaymentMethod>>() {
            override fun onSuccess(t: ArrayList<PaymentMethod>) {
                if (t.isNotEmpty()) {
                    mView?.showDefaultPaymentMethod(t[0])
                }
                mView?.hideProgressBar()
                this.dispose()
            }

            override fun onError(e: Throwable) {
                mView?.hideProgressBar()
                this.dispose()
            }

        })
    }

    override fun afterTextChanged(p0: Editable?) {
        p0?.let {
            mView?.removeTextWatcher()

            var p0String = p0.toString()
            p0String = p0String.replace("$", "")
            p0String = p0String.replace(",", "")
            p0String = p0String.replace(".", "")
            p0String = p0String.replace("^\\d*$", "")
            if (!TextUtils.isEmpty(p0String) && TextUtils.isDigitsOnly(p0String)) {
                val format = NumberFormat.getCurrencyInstance(Locale.US)
                format.maximumFractionDigits = 0
                val textToDisplay = format.format(p0String.toLong())
                textToDisplay?.let {
                    p0.clear()
                    p0.insert(0, textToDisplay)
                }
            }else{
                p0.clear()
            }

            mView?.addTextWatcher()

        }


    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onCreate(bundle: Bundle?) {
        mView?.initComponents()
        getPaymentMethods()
        mView?.setTotal(ShoppingCart.instance.getTotalInitialValue())
    }

    override fun onDestroy() {
        mView = null
    }
}