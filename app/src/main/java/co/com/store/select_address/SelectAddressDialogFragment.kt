package co.com.store.select_address

import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.Location
import co.com.store.R
import co.com.store.checkout.ICheckoutCallbacks
import co.com.store.location.LocationAdapter
import kotlinx.android.synthetic.main.select_address_fragment.*

class SelectAddressDialogFragment : DialogFragment(), ISelectedAddressFragmentView {


    private val mPresenter: ISelectAddressFragmentPresenter = SelectAddressFragmentPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.bind(this)


    }

    override fun showProgresBar() {
        mPB?.visibility  = View.VISIBLE
        mCLContent?.visibility = View.GONE

    }

    override fun hideProgresBar() {
        mPB?.visibility  = View.GONE
        mCLContent?.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(resources.displayMetrics.widthPixels - 10,
                resources.displayMetrics.heightPixels - 100)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.select_address_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onCreate(null)

    }

    override fun initComponents() {
        mToolbar?.title = "Select an address"
        mToolbar?.setTitleTextColor(Color.WHITE)
        mRVLocations?.setHasFixedSize(false)
        mRVLocations?.layoutManager = LinearLayoutManager(activity)
        mRVLocations?.adapter = LocationAdapter(ArrayList(), mPresenter)
        mCVSuggestedAddress?.setOnClickListener {
            mPresenter.suggestedAddressClick()
        }
    }

    override fun showAddresses(locations: ArrayList<Location>) {
        mRVLocations?.adapter?.let {
            (mRVLocations.adapter as LocationAdapter).add(locations)
            (mRVLocations.adapter as LocationAdapter).hideFavorites()
        }
    }

    override fun dismissDialog() {
        dismissAllowingStateLoss()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mPresenter.setCheckoutCallbacks(activity as ICheckoutCallbacks)
    }

    override fun showSuggestedAddress(location: Location) {
        mTVLocationName?.text = location.mName
        mTVLocationAddress?.text = location.mAddress
    }


    companion object {


        fun newInstance(): SelectAddressDialogFragment = SelectAddressDialogFragment()
    }
}// Required empty public constructor
