package co.com.store.categories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.core.Category
import co.com.store.R
import com.squareup.picasso.Picasso

/**
 * Created by oscarg798 on 10/13/17.
 */
class CategoriesAdapter(private val mCategories: ArrayList<Category>,
                        private val mOnCategoryClickListener: CategoryClickListener) :
        RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onBindViewHolder(holder: CategoryViewHolder?, position: Int) {
        holder?.let {
            holder.mTVCategoryName.text = mCategories[position].mName
            Picasso.with(holder.itemView.context).load(mCategories[position].mAvatar)
                    .into(holder.mIVCategoryAvatar)
            holder.itemView.setOnClickListener {
                mOnCategoryClickListener.onCategoryClicked(it, mCategories[position])
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_view_holder, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position % 2) {
            0 -> 1
            else -> 2
        }
    }

    override fun getItemCount(): Int = mCategories.size

    interface CategoryClickListener {
        fun onCategoryClicked(view: View, category: Category)
    }

    fun clear() {
        mCategories.clear()
        notifyDataSetChanged()
    }

    fun add(categories: ArrayList<Category>) {
        mCategories.addAll(categories)
        notifyDataSetChanged()
    }


}