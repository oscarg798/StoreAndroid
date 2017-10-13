package co.com.store.categories

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.store.R
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import kotlinx.android.synthetic.main.product_view_holder.view.*

/**
 * Created by oscarg798 on 10/13/17.
 */
class ProductViewHolder(mItemView: View) : ChildViewHolder(mItemView) {

    val mIVProductAvatar = mItemView.findViewById<ImageView>(R.id.mIVProductAvatar)
    val mTVProductName = mItemView.findViewById<TextView>(R.id.mTVProductName)
    val mTVProductDescription = mItemView.findViewById<TextView>(R.id.mTVProductDescription)
}