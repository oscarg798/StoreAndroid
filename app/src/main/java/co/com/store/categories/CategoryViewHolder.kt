package co.com.store.categories

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.store.R
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder

/**
 * Created by oscarg798 on 10/13/17.
 */
class CategoryViewHolder(mItemView: View) : GroupViewHolder(mItemView) {

    val mIVCategoryAvatar = mItemView.findViewById<ImageView>(R.id.mIVCategoryAvatar)
    val mTVCategoryName = mItemView.findViewById<TextView>(R.id.mTVCategoryName)
}