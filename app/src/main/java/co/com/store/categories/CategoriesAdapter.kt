package co.com.store.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import co.com.core.use_cases.Product
import co.com.store.R
import com.squareup.picasso.Picasso
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

/**
 * Created by oscarg798 on 10/13/17.
 */
class CategoriesAdapter(private val mCategories: ArrayList<ViewCategory>) :
        ExpandableRecyclerViewAdapter<CategoryViewHolder, ProductViewHolder>(mCategories) {


    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_view_holder, parent,
                false)
        return ProductViewHolder(view)
    }

    override fun onBindGroupViewHolder(holder: CategoryViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?) {
        group?.let {
            holder?.mTVCategoryName?.text = mCategories[flatPosition].mTitle.toUpperCase()
            holder?.mTVProductsCount?.text = "${mCategories[flatPosition].products.size}"
            holder?.itemView?.let {
                holder.itemView.setOnTouchListener { view, _ ->
                    if (isGroupExpanded(flatPosition)) {
                        holder.mIVExpandIcon?.setImageDrawable(view.resources.getDrawable(R.drawable.ic_expand_more))
                    } else {
                        holder.mIVExpandIcon?.setImageDrawable(view.resources.getDrawable(R.drawable.ic_expand_less))

                    }
                    false
                }

            }
        }
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_view_holder, parent,
                false)
        return CategoryViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: ProductViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?, childIndex: Int) {
        group?.let {
            holder?.let {
                val product = group.items[childIndex] as Product
                holder.mTVProductName?.text = product.mName
                holder.mTVProductDescription?.text = product.mDescription
                product.mImages?.let{
                    if(product.mImages!!.isNotEmpty()){
                        Picasso.with(holder.mIVProductAvatar.context)
                                .load(product.mImages!![0]).into(holder.mIVProductAvatar)
                    }
                }
            }


        }
    }

    fun add(categories:ArrayList<ViewCategory>){
        mCategories.addAll(categories)
        notifyDataSetChanged()
    }

    fun clear(){
        mCategories.clear()
        notifyDataSetChanged()
    }


}