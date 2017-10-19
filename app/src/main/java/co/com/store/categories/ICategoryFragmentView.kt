package co.com.store.categories

import co.com.core.Category
import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 10/13/17.
 */
interface ICategoryFragmentView : IBaseView {

    fun showCategories(categories :ArrayList<Category>)

    fun showProgressBar()

    fun hideProgressBar()

    fun clearCategories()

}