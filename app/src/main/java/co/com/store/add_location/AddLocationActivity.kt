package co.com.store.add_location

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import co.com.data.PLACES_REQUEST_CODE
import co.com.store.R
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import kotlinx.android.synthetic.main.activity_add_location.*


class AddLocationActivity : AppCompatActivity(), IAddLocationActivityView {

    private val mPresenter: IAddLocationActivityPresenter = AddLocationActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)
        mPresenter.bind(this)
        mPresenter.onCreate(null)
    }

    override fun initComponents() {
        mSRLAddLocation?.isEnabled = false
        mETAddress?.setOnClickListener {
            val typeFilter = AutocompleteFilter.Builder()
                    .build()
            try {
                val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                        .setFilter(typeFilter)
                        .build(this)

                startActivityForResult(intent, PLACES_REQUEST_CODE)
            } catch (e: GooglePlayServicesRepairableException) {
                e.printStackTrace()
            } catch (e: GooglePlayServicesNotAvailableException) {
                e.printStackTrace()
            }

        }

        mBTNAddAddress?.setOnClickListener {
            if (mETAddress !== null && mETAddressName !== null
                    && mETAddressIndications !== null &&
                    !TextUtils.isEmpty(mETAddress.text) &&
                    !TextUtils.isEmpty(mETAddressName.text)) {

                mPresenter.addLocation(mETAddressName.text.toString(),mETAddress.text.toString(),
                        mETAddressIndications.text.toString())

            }

        }

    }

    override fun onAddressAdded() {
        finish()
    }

    override fun setAddress(address: String) {
        mETAddress?.setText(address)
    }

    override fun setAddressName(addressName: String) {
        mETAddressName?.setText(addressName)
    }

    override fun showProgressBar() {
        mSRLAddLocation?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLAddLocation?.isRefreshing = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PLACES_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            mPresenter.getDataFromPlace(PlaceAutocomplete.getPlace(this, data))


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}
