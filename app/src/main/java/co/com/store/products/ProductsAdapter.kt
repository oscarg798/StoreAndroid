package co.com.store.products

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import co.com.core.ShoppingCart
import co.com.core.use_cases.Product
import co.com.store.R
import com.squareup.picasso.Picasso

/**
 * Created by oscarg798 on 10/18/17.
 */
class ProductsAdapter(private val mProducts: ArrayList<Product>,
                      private val mProductCallbacks: ProductCallbacks) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onBindViewHolder(holder: ProductViewHolder?, position: Int) {
        holder?.let {
            holder.mTVProductName.text = mProducts[position].mName
            holder.mTVProductDescription.text = mProducts[position].mDescription
            holder.mTVPrice.text = "$${mProducts[position].mPrice}"
            holder.mTVCount.text = "${ShoppingCart.instance.getQuantityForProduct(mProducts[position].mUuid)}"
            mProducts[position].mImages?.let {
                if (mProducts[position].mImages!!.isNotEmpty()) {
                    Picasso.with(holder.itemView.context).load(mProducts[position].mImages!![0])
                            .into(holder.mIVProductAvatar)
                }
            }

            holder.mIVRemoveProductFromCart.setOnClickListener {
                if (!TextUtils.isEmpty(holder.mTVCount.text)) {
                    var count = Integer.parseInt(holder.mTVCount.text.toString())
                    if (count > 0) {
                        count--
                        holder.mTVCount.text = "$count"
                        mProductCallbacks.addOrRemoveProductFromShoppingCart(mProducts[position], count)
                    }

                }

            }

            holder.mIVAddProductToCart.setOnClickListener {
                if (!TextUtils.isEmpty(holder.mTVCount.text)) {
                    var count = Integer.parseInt(holder.mTVCount.text.toString())
                    count++
                    holder.mTVCount.text = "$count"
                    mProductCallbacks.addOrRemoveProductFromShoppingCart(mProducts[position], count)
                } else {
                    holder.mTVCount.text = "${1}"
                    mProductCallbacks.addOrRemoveProductFromShoppingCart(mProducts[position], 1)

                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_view_holder, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }

    fun clear() {
        mProducts.clear()
        notifyDataSetChanged()
    }

    fun add(products: List<Product>) {
        mProducts.addAll(products)
        notifyDataSetChanged()
    }


}