package co.com.data.repositories

import co.com.data.APICategory
import co.com.data.AppDatabase
import co.com.data.entities.DBCategory
import co.com.data.network.ICategoryRoute
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oscarg798 on 10/11/17.
 */
class CategoryRepository : ICategoryRepository {

    @Inject
    lateinit var mCategoriesRoute: ICategoryRoute

    @Inject
    lateinit var mDatabase:AppDatabase


    override fun getCategories(): Observable<List<APICategory>> {
        return mCategoriesRoute.getCategories()
    }

    override fun saveCategories(categories: ArrayList<DBCategory>) {
        categories.forEach {
            mDatabase.apiCategoryModel().insertCategory(it)
        }

    }

    override fun getCategory(uuid: String): DBCategory {
        return mDatabase.apiCategoryModel().getCategoryById(uuid)
    }
}