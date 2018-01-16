package co.com.store.shopping_cart

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import co.com.core.Product
import co.com.store.R
import co.com.store.products.ProductCallbacks
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

/**
 * Created by oscarg798 on 10/24/17.
 */
class ShoppingCartAdapter(val mProductsInCart: ArrayList<Pair<Product, Int>>,
                          val mProductCallbacks: ProductCallbacks) :
        RecyclerView.Adapter<ShoppingCartViewHolder>() {
    override fun onBindViewHolder(holder: ShoppingCartViewHolder?, position: Int) {
        holder?.let {
            holder.mTVProductName.text = mProductsInCart[position].first.mName
            var price = mProductsInCart[position]
                    .first.mPrice.replace(".", "").toLong() *
                    mProductsInCart[position].second
            val format =  NumberFormat.getCurrencyInstance(Locale.US)


            holder.mTVPrice.text = "${format.format(price)}."
            holder.mTVCount.text = "${mProductsInCart[position].second}"
            if (mProductsInCart[position]
                    .first.mImages !== null && mProductsInCart[position]
                    .first.mImages!!.isNotEmpty()) {
                Picasso.with(holder.mIVProductAvatar.context)
                        .load(mProductsInCart[position]
                                .first.mImages!![0])
                        .into(holder.mIVProductAvatar)
            }

            holder.mIVRemoveProductFromCart.setOnClickListener {
                if (!TextUtils.isEmpty(holder.mTVCount.text)) {
                    var count = Integer.parseInt(holder.mTVCount.text.toString())
                    if (count > 0) {
                        count--
                        holder.mTVCount.text = "$count"
                        mProductCallbacks.addOrRemoveProductFromShoppingCart(mProductsInCart[position]
                                .first, count)
                    }

                }

            }

            holder.mIVAddProductToCart.setOnClickListener {
                if (!TextUtils.isEmpty(holder.mTVCount.text)) {
                    var count = Integer.parseInt(holder.mTVCount.text.toString())
                    count++
                    holder.mTVCount.text = "$count"
                    mProductCallbacks.addOrRemoveProductFromShoppingCart(mProductsInCart[position]
                            .first, count)
                } else {
                    holder.mTVCount.text = "${1}"
                    mProductCallbacks.addOrRemoveProductFromShoppingCart(mProductsInCart[position]
                            .first, 1)

                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_cart_view_holder, parent, false)
        return ShoppingCartViewHolder(view)

    }

    override fun getItemCount(): Int = mProductsInCart.size

    fun add(products: ArrayList<Pair<Product, Int>>) {
        mProductsInCart.addAll(products)
        notifyDataSetChanged()
    }

    fun updateProductQuantity(productUuid:String, quantity:Int){
        var index = 0
        for((product, quant) in mProductsInCart){
            if(product.mUuid==productUuid){
                if(quantity>0){
                    mProductsInCart.removeAt(index)
                    mProductsInCart.add(index, Pair(product, quantity))
                }else{
                    mProductsInCart.removeAt(index)
                }

                break
            }
            index++
        }
        if(quantity>0){
            notifyItemChanged(index)
        }else{
            notifyDataSetChanged()
        }


    }


}