package co.com.store.location


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.Location

import co.com.store.R
import kotlinx.android.synthetic.main.fragment_location.*
import kotlinx.android.synthetic.main.fragment_products.*


/**
 * A simple [Fragment] subclass.
 */
class LocationFragment : Fragment(), ILocationFragmentView {


    private val mPresenter: ILocationFragmentPresenter = LocationFragmentPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.bind(this)
        mPresenter.onCreate(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_location, container, false)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onViewCreated()
    }

    override fun initComponents() {
        mSRLLocation?.isEnabled = false
        mRVLocations?.setHasFixedSize(false)
        mRVLocations?.layoutManager = LinearLayoutManager(activity)
        mRVLocations?.adapter = LocationAdapter(ArrayList())
    }

    override fun showLocations(locations: List<Location>) {
        mRVLocations?.adapter?.let {
            (mRVLocations.adapter as LocationAdapter).add(locations)
        }
    }

    override fun showProgressBar() {
        mSRLLocation?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLLocation?.isRefreshing = false
    }

    companion object {
        fun newInstance():LocationFragment = LocationFragment()
    }
}
