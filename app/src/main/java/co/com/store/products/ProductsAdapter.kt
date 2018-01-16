package co.com.store.products

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.ShoppingCart
import co.com.core.Product
import co.com.store.R
import com.squareup.picasso.Picasso

/**
 * Created by oscarg798 on 10/18/17.
 */
class ProductsAdapter(private val mProducts: ArrayList<Product>,
                      private val mIOpenProductDetailCallback: IOpenProductDetailCallback) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onBindViewHolder(holder: ProductViewHolder?, position: Int) {
        holder?.let {
            holder.mTVProductName.text = mProducts[position].mName
            holder.mTVProductDescription.text = mProducts[position].mDescription
            holder.mTVPrice.text = "$${mProducts[position].mPrice}"
            if (ShoppingCart.instance.isProductOnCart(mProducts[position].mUuid)) {
                holder.mTVQuantity.visibility = View.VISIBLE
                holder.mTVQuantity.text = ShoppingCart.instance
                        .getQuantityForProduct(mProducts[position].mUuid).toString()
            } else {
                holder.mTVQuantity.visibility = View.GONE
            }

            mProducts[position].mImages?.let {
                if (mProducts[position].mImages!!.isNotEmpty()) {
                    Picasso.with(holder.itemView.context).load(mProducts[position].mImages!![0])
                            .into(holder.mIVProductAvatar)
                }
            }

            holder.itemView.setOnClickListener {
                mIOpenProductDetailCallback.open(mProducts[position])
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

    fun notifyChange(){
        notifyDataSetChanged()
    }

    fun add(products: List<Product>) {
        mProducts.addAll(products)
        notifyDataSetChanged()
    }


}