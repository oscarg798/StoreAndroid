package co.com.store.products.detail


import android.app.DialogFragment
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.Product
import co.com.data.PRODUCT
import co.com.store.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product_detail.*


class ProductDetailDialogFragment : DialogFragment(),
        IProductDetailView {

    private val mPresenter: IProductDetailPresenter = ProductDetailPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            mPresenter.bind(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.onCreate(arguments)

    }

    override fun initComponents() {
        mBTNAddToCart?.setOnClickListener {
            mPresenter.btnAddToCartPressed()
            dismissAllowingStateLoss()
        }

        mIVClose?.setOnClickListener {
            dismissAllowingStateLoss()
        }

        mIVAdd?.setOnClickListener {
            mPresenter.addOrRemoveFromQuantity(true)
        }
        mIVSubstract?.setOnClickListener {
            mPresenter.addOrRemoveFromQuantity(false)
        }

    }

    override fun setProductName(name: String) {
        mTVProductName?.text = name
    }

    override fun setProductImage(image: String) {
        activity?.let {
            mIVProductAvatar?.let {
                Picasso.with(activity).load(image).into(mIVProductAvatar)
            }
        }
    }

    override fun setProductDescription(description: String) {
        mTVProductDescription?.text = description
    }

    override fun setProductQuantity(quantity: String) {
        mTVActualQuantity?.text = quantity
    }

    override fun setBTNAddToCartText(stringResourceId: Int) {
        val drawable = mBTNAddToCart?.background as? GradientDrawable

        when (stringResourceId) {
            R.string.add_to_cart_label -> {
                mBTNAddToCart?.isEnabled = true
                drawable?.setColor(resources.getColor(R.color.colorPrimary))
            }
            R.string.remove_from_cart_label -> {
                mBTNAddToCart?.isEnabled = true
                drawable?.setColor(resources.getColor(R.color.colorPrimary))
            }
            R.string.update_cart_label -> {
                mBTNAddToCart?.isEnabled = true
                drawable?.setColor(resources.getColor(R.color.colorPrimary))
            }
            else -> {
                mBTNAddToCart?.isEnabled = false
                drawable?.setColor(resources.getColor(R.color.silver))
            }
        }
        mBTNAddToCart?.text = getString(stringResourceId)
    }

    override fun setPrice(price: String) {
        mTVPrice?.text = price
    }

    companion object {
        fun newInstance(product: Product): ProductDetailDialogFragment {
            val fragment = ProductDetailDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(PRODUCT, product)
            fragment.arguments = bundle
            return fragment
        }
    }
}
