package co.com.store.dashboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.com.store.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), IDashboardView {

    private val mPresenter:IDashboardActivityPresenter= DashboardPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mPresenter.bind(this)
        mPresenter.onCreate()

    }

    override fun initComponents() {
        mNavigation?.setOnNavigationItemSelectedListener(mPresenter)
    }
}
