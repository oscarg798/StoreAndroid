package co.com.store.shopping_cart

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.store.R

/**
 * Created by oscarg798 on 10/24/17.
 */
class ShoppingCartViewHolder(val mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    val mIVProductAvatar = mItemView.findViewById<ImageView>(R.id.mIVProductAvatar)
    val mIVAddProductToCart = mItemView.findViewById<ImageView>(R.id.mIVAddProductToCart)
    val mIVRemoveProductFromCart = mItemView.findViewById<ImageView>(R.id.mIVRemoveProductFromCart)
    val mTVProductName = mItemView.findViewById<TextView>(R.id.mTVProductName)
    val mTVPrice = mItemView.findViewById<TextView>(R.id.mTVPrice)
    val mTVCount = mItemView.findViewById<TextView>(R.id.mTVCount)

}