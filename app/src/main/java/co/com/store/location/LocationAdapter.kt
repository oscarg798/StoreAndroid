package co.com.store.location

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.Location
import co.com.store.R

/**
 * Created by oscarg798 on 10/19/17.
 */
class LocationAdapter(private var mLocations: ArrayList<Location>,
                      private val mLocationAdapterCallbacks: LocationAdapterCallbacks) : RecyclerView.Adapter<LocationViewHolder>() {

    private var mHideFavorites = false

    override fun onBindViewHolder(holder: LocationViewHolder?, position: Int) {
        holder?.let {
            holder.mTVLocationAdress.text = mLocations[position].mAddress
            holder.mTVLocationName.text = mLocations[position].mName
            holder.mIVFavorite.setImageDrawable(if (mLocations[position].mFavorite)
                holder.mIVFavorite.context.resources
                        .getDrawable(R.drawable.ic_favorite) else
                holder.mIVFavorite.context.resources
                        .getDrawable(R.drawable.ic_favorite_border))

            holder.mIVFavorite.setOnClickListener {
                if (mLocations[position].mFavorite) {
                    mLocationAdapterCallbacks.removeLocationFromFavorite(mLocations[position].mUuid)
                } else {
                    mLocationAdapterCallbacks.makeLocationFavorite(mLocations[position].mUuid)
                }

            }

            if(mHideFavorites){
                holder.mIVFavorite?.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = mLocations.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_view_holder, parent, false)
        return LocationViewHolder(view)
    }

    fun updateLocationFavorite(uuid: String, favorite: Boolean) {
        mLocations.forEach {
            if (favorite) {
                it.mFavorite = it.mUuid == uuid
            } else {
                it.mFavorite = false
            }

        }

        mLocations = ArrayList(mLocations.sortedByDescending { it.mFavorite })
        notifyDataSetChanged()
    }

    fun hideFavorites(){
        mHideFavorites = true
        notifyDataSetChanged()
    }
    fun add(locations: List<Location>) {
        mLocations.addAll(locations)
        notifyDataSetChanged()
    }

    fun clear() {
        mLocations.clear()
        notifyDataSetChanged()
    }
}