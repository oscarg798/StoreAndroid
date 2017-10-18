package co.com.store.products

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.com.core.use_cases.Product
import co.com.store.R
import com.squareup.picasso.Picasso

/**
 * Created by oscarg798 on 10/18/17.
 */
class ProductsAdapter(private val mProducts: ArrayList<Product>) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onBindViewHolder(holder: ProductViewHolder?, position: Int) {
        holder?.let {
            holder.mTVProductName.text = mProducts[position].mName
            holder.mTVProductDescription.text = mProducts[position].mDescription
            holder.mTVPrice.text = "$${mProducts[position].mPrice}"
            mProducts[position].mImages?.let {
                if (mProducts[position].mImages!!.isNotEmpty()) {
                    Picasso.with(holder.itemView.context).load(mProducts[position].mImages!![0])
                            .into(holder.mIVProductAvatar)
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

    fun clear(){
        mProducts.clear()
        notifyDataSetChanged()
    }

    fun add(products:List<Product>){
        mProducts.addAll(products)
        notifyDataSetChanged()
    }


}