package co.com.store.categories

import co.com.core.Product
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

/**
 * Created by oscarg798 on 10/13/17.
 */
data class ViewCategory(val mTitle: String, val products: ArrayList<Product>) :
        ExpandableGroup<Product>(mTitle, products)