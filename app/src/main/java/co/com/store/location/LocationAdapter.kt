package co.com.store.location

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.com.core.Location
import co.com.store.R

/**
 * Created by oscarg798 on 10/19/17.
 */
class LocationAdapter(val mLocations: ArrayList<Location>) : RecyclerView.Adapter<LocationViewHolder>() {

    override fun onBindViewHolder(holder: LocationViewHolder?, position: Int) {
        holder?.let {
            holder.mTVLocationAdress.text = mLocations[position].mAddress
            holder.mTVLocationName.text = mLocations[position].mName
        }
    }

    override fun getItemCount(): Int = mLocations.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_view_holder, parent, false)
        return LocationViewHolder(view)
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