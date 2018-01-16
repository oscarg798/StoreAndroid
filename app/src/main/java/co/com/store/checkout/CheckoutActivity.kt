package co.com.store.checkout

import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import co.com.core.Location
import co.com.core.PaymentMethod
import co.com.core.ShoppingCart
import co.com.data.SELECT_ADDRESS
import co.com.store.R
import co.com.store.select_address.SelectAddressDialogFragment
import kotlinx.android.synthetic.main.activity_checkout.*
import java.text.NumberFormat
import java.util.*

class CheckoutActivity : AppCompatActivity(), ICheckoutCallbacks {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        initComponents()


    }

    private fun initComponents() {

        mTVAddress?.setOnClickListener { v ->
            showDialogFragment(SelectAddressDialogFragment.newInstance(), SELECT_ADDRESS)
        }

        val total = ShoppingCart.instance.getTotalInitialValue()
        mTVTotalPrice?.text = "Total: $total"
        val deliverCost = 2000
        mTVDeliverCost?.text = NumberFormat.getCurrencyInstance(Locale.US).format(deliverCost)


        val totalCost = NumberFormat.getCurrencyInstance(Locale.US).parse(total).toInt() + deliverCost
        mBTNPlaceOrder?.text = "Place order ${NumberFormat.getCurrencyInstance(Locale.US).format(totalCost)}"
    }

    override fun onAddressSelected(location: Location) {
        mTVAddress?.text = location.mAddress

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
