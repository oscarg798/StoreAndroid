package co.com.store.select_address

import android.text.SpannableString
import co.com.core.Location
import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 11/2/17.
 */
interface ISelectedAddressFragmentView : IBaseView {

    fun showAddresses(locations: ArrayList<Location>)

    fun showSuggestedAddress(location: Location)

    fun dismissDialog()

    fun showProgresBar()

    fun hideProgresBar()



}