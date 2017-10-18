package co.com.store

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.com.core.use_cases.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val product = intent.extras["product"] as Product
        supportActionBar?.title = product.mName
        Picasso.with(this).load(product.mImages!![0]).into(mIVProductAvatar)

    }
}
