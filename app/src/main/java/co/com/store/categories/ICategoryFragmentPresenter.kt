package co.com.store.categories

import android.support.v4.widget.SwipeRefreshLayout
import co.com.store.dashboard.IBasePresenter

/**
 * Created by oscarg798 on 10/13/17.
 */
interface ICategoryFragmentPresenter:IBasePresenter, SwipeRefreshLayout.OnRefreshListener{

    fun onViewCreated()
}