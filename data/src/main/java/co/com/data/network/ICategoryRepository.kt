package co.com.data.network

import co.com.data.APICategory
import io.reactivex.Observable

/**
 * Created by oscarg798 on 10/11/17.
 */
interface ICategoryRepository {

    fun getCategories(): Observable<List<APICategory>>
}