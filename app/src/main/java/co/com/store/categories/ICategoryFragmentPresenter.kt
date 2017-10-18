package co.com.store.categories

import android.support.v4.widget.SwipeRefreshLayout
import co.com.store.dashboard.IBasePresenter
import co.com.store.utils.FragmentCallback

/**
 * Created by oscarg798 on 10/13/17.
 */
interface ICategoryFragmentPresenter : IBasePresenter, SwipeRefreshLayout.OnRefreshListener,
        CategoriesAdapter.CategoryClickListener {

    fun onViewCreated()

    fun bindFragmentCallback(callback: FragmentCallback)
}