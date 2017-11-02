package co.com.store.shopping_cart

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.use_cases.Product
import co.com.store.R
import co.com.store.checkout.CheckoutActivity
import kotlinx.android.synthetic.main.fragment_shopping_cart.*


class ShoppingCartFragment : Fragment(), IShoppingCartFragmentView {

    private val mPresenter: IShoppingCartFragmentPresenter = ShoppingCartFragmentPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.bind(this)
        mPresenter.onCreate(null)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onViewCreated()
    }

    override fun initComponents() {
        mSRLShoppingCart?.isEnabled = false
        mRVShoppingCart?.setHasFixedSize(false)
        mRVShoppingCart?.layoutManager = LinearLayoutManager(activity)
        mRVShoppingCart?.adapter = ShoppingCartAdapter(ArrayList(), mPresenter)
        mBTNCheckout?.setOnClickListener {
            activity?.let {
                activity.startActivity(Intent(activity, CheckoutActivity::class.java))
            }
        }

    }

    override fun showProductsOnShoppingCart(products: ArrayList<Pair<Product, Int>>) {
        mRVShoppingCart?.adapter?.let {
            (mRVShoppingCart.adapter as ShoppingCartAdapter).add(products)
        }
    }

    override fun changeTotal(total: String) {
        activity?.let {
            val textToDisplay = "Total: $total"
            val spannable= SpannableString(textToDisplay)
            spannable.setSpan(ForegroundColorSpan(activity.resources.getColor(R.color.colorPrimary)),
                    textToDisplay.indexOf(":") + 1, textToDisplay.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            mTVTotalPrice?.text = spannable
        }




    }

    override fun updateProductQuantity(productUuid: String, quantity: Int) {
        mRVShoppingCart?.adapter?.let {
            (mRVShoppingCart.adapter as ShoppingCartAdapter).updateProductQuantity(productUuid, quantity)
        }
    }

    override fun showProgressBar() {
        mSRLShoppingCart?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLShoppingCart?.isRefreshing = false
    }

    companion object {
        fun newInstance(): ShoppingCartFragment = ShoppingCartFragment()
    }
}// Required empty public constructor
