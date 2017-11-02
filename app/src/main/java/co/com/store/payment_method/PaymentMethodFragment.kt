package co.com.store.payment_method

import android.app.Fragment
import android.os.Bundle
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.PaymentMethod
import co.com.core.ShoppingCart
import co.com.store.R
import kotlinx.android.synthetic.main.fragment_payment_method.*


class PaymentMethodFragment : Fragment(), IPaymentMethodFragmentView {

    private val mPresenter: IPaymentMethodFragmentPresenter = PaymentMethodFragmentPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.bind(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_method, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onCreate(null)
    }

    override fun setTotal(total: String) {
        val textToDisplay = "Total to pay: $total"
        val spannable = SpannableString(textToDisplay)
        spannable.setSpan(ForegroundColorSpan(activity.resources.getColor(R.color.colorPrimary)),
                textToDisplay.indexOf(":") + 1, textToDisplay.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        mTVTotalPrice?.text = spannable
    }

    override fun initComponents() {
        mSRLPaymentMethod?.isEnabled = false
        addTextWatcher()
    }


    override fun showDefaultPaymentMethod(paymentMethod: PaymentMethod) {
        mTVPaymentMethodName?.text = paymentMethod.mName
    }

    override fun showProgressBar() {
        mSRLPaymentMethod?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLPaymentMethod?.isRefreshing = false
    }

    override fun removeTextWatcher() {
        mETAmmount?.removeTextChangedListener(mPresenter)

    }

    override fun addTextWatcher() {
        mETAmmount?.addTextChangedListener(mPresenter)

    }

    companion object {

        fun newInstance(): PaymentMethodFragment {

            return PaymentMethodFragment()
        }
    }
}// Required empty public constructor
