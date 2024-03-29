package co.com.store.products


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.Product
import co.com.data.CATEGORY_UUID_KEY
import co.com.data.PRODUCT_DETAIL

import co.com.store.R
import co.com.store.products.detail.ProductDetailDialogFragment
import kotlinx.android.synthetic.main.fragment_products.*


class ProductsFragment : Fragment(), IProductFragmentView {

    private val mPresenter: IProductFragmentPresenter = ProductFragmentPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.bind(this)
        mPresenter.onCreate(arguments)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onViewCreated()

    }

    override fun initComponents() {
        mSRLProducts?.isEnabled = false
        mRVProducts?.setHasFixedSize(false)
        mRVProducts?.layoutManager = LinearLayoutManager(activity)
        mRVProducts?.adapter = ProductsAdapter(ArrayList(), mPresenter)
        mPresenter.getProducts()

    }

    override fun showProducts(products: List<Product>) {
        mRVProducts?.adapter?.let {
            (mRVProducts.adapter as ProductsAdapter).add(products)
        }
    }

    override fun showProgressBar() {
        mSRLProducts?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLProducts?.isRefreshing = false
    }


    override fun notifyDatasetHasChange() {
        (mRVProducts?.adapter as? ProductsAdapter)?.notifyChange()

    }

    override fun showProductDetail(productDetailDialogFragment: ProductDetailDialogFragment) {
        val ft = fragmentManager.beginTransaction()
        val prev = fragmentManager.findFragmentByTag("PRODUCT_DETAIL")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)

        productDetailDialogFragment.show(ft, PRODUCT_DETAIL)
    }

    companion object {

        fun newInstance(categoryUuid: String): ProductsFragment {
            val fragment = ProductsFragment()
            val args = Bundle()
            args.putString(CATEGORY_UUID_KEY, categoryUuid)
            fragment.arguments = args
            return fragment
        }
    }

}