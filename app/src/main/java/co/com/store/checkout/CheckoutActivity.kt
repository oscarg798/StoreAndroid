package co.com.store.checkout

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.*
import co.com.core.Location
import co.com.core.PaymentMethod
import co.com.store.R
import co.com.store.payment_method.PaymentMethodFragment
import co.com.store.select_address.SelectAddressFragment
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.fragment_checkout.view.*

class CheckoutActivity : AppCompatActivity(), ICheckoutCallbacks {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: CheckoutPageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter =
                CheckoutPageAdapter(fragmentManager, arrayListOf(SelectAddressFragment.newInstance(),
                        PaymentMethodFragment.newInstance()))

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter
        indicator?.setViewPager(container)


    }

    override fun onAddressSelected(location: Location) {
        container?.currentItem = 1
    }

    override fun onPaymentMethodSelected(paymentMethod: PaymentMethod) {

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_checkout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


}
