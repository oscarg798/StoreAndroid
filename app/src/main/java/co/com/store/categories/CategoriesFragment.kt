package co.com.store.categories

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.Category
import co.com.core.use_cases.Product
import co.com.store.ProductDetailActivity
import co.com.store.R
import co.com.store.utils.FragmentCallback
import co.com.store.utils.RecyclerViewDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_blank.*


/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : Fragment(), ICategoryFragmentView {


    private val mPresenter: ICategoryFragmentPresenter = CategoriesFragmentPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.bind(this)
        mPresenter.bindFragmentCallback(activity as FragmentCallback)
        mPresenter.onCreate(null)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onViewCreated()

    }

    override fun initComponents() {
        mRVCategories?.setHasFixedSize(false)
        mRVCategories?.layoutManager = LinearLayoutManager(activity)
        mRVCategories?.adapter = CategoriesAdapter(ArrayList(), mPresenter)
        mRVCategories?.addItemDecoration(RecyclerViewDividerItemDecoration(activity.resources.
                getDrawable(R.drawable.horizontal_divider)))
        mSRLCategories?.setOnRefreshListener(mPresenter)
    }

    override fun clearCategories() {
        mRVCategories?.adapter?.let {
            (mRVCategories.adapter as CategoriesAdapter).clear()
        }
    }


    override fun showCategories(categories: ArrayList<Category>) {
        mRVCategories?.adapter?.let {
            (mRVCategories.adapter as CategoriesAdapter).add(categories)
        }
    }


    override fun showProgressBar() {
        mSRLCategories?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLCategories?.isRefreshing = false
    }

    companion object {
        fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }
    }


}
