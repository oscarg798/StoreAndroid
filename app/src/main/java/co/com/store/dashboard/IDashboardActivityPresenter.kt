package co.com.store.dashboard

import android.support.design.widget.BottomNavigationView
import co.com.store.products.ProductCallbacks
import co.com.store.utils.FragmentCallback

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IDashboardActivityPresenter : IBasePresenter<IDashboardView>, BottomNavigationView.OnNavigationItemSelectedListener,
        FragmentCallback