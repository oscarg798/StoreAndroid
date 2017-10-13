package co.com.store.categories

import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 10/13/17.
 */
interface ICategoryFragmentView : IBaseView {

    fun showCategoriesAndProducts(categories: ArrayList<ViewCategory>)

    fun showProgressBar()

    fun hideProgressBar()

    fun clearCategories()
}