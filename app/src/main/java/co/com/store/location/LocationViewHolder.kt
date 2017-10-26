package co.com.store.location

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.store.R
import kotlinx.android.synthetic.main.location_view_holder.view.*

/**
 * Created by oscarg798 on 10/19/17.
 */
class LocationViewHolder(mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    val mTVLocationName = mItemView.findViewById<TextView>(R.id.mTVLocationName)
    val mTVLocationAdress = mItemView.findViewById<TextView>(R.id.mTVLocationAddress)
    val mIVFavorite = mItemView.findViewById<ImageView>(R.id.mIVFavorite)
}

