package co.com.store.checkout

import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import co.com.core.Location
import co.com.core.PaymentMethod
import co.com.data.SELECT_ADDRESS
import co.com.store.R
import co.com.store.select_address.SelectAddressDialogFragment
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity(), ICheckoutCallbacks,
        ICheckoutActivityView {


    private val mPresenter: ICheckoutActivityPresenter = CheckoutActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        mPresenter.bind(this)
    }

    override fun initComponents() {
        supportActionBar?.title = "Checkout"
        mTVAddress?.setOnClickListener {
            showDialogFragment(SelectAddressDialogFragment.newInstance(), SELECT_ADDRESS)
        }


    }

    override fun onAddressSelected(location: Location) {
        mTVAddress?.text = location.mAddress

    }

    override fun showBTNPlaceOrderText(text: String) {
        mBTNPlaceOrder?.text = text
    }

    override fun showFavoriteLocation(address: String) {
        mTVAddress?.text = address

    }

    override fun showTotalInCart(total: String) {
        mTVTotalPrice?.text = total
    }

    override fun showDeliverCost(deliverCost: String) {
        mTVDeliverCost?.text = deliverCost
    }

    fun showDialogFragment(dialog: DialogFragment, tag: String) {
        val ft = fragmentManager.beginTransaction()
        val prev = fragmentManager.findFragmentByTag(tag)
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)

        dialog.show(ft, tag)
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
