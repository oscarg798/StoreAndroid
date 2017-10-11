package co.com.data.network

import co.com.data.APICategory
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oscarg798 on 10/11/17.
 */
class CategoryRepository : ICategoryRepository {

    @Inject
    lateinit var mCategoriesRoute: ICategoryRoute


    override fun getCategories(): Observable<List<APICategory>> {
        return mCategoriesRoute.getCategories()
    }
}