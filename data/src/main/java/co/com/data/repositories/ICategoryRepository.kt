package co.com.data.repositories

import co.com.data.APICategory
import co.com.data.entities.DBCategory
import io.reactivex.Observable

/**
 * Created by oscarg798 on 10/11/17.
 */
interface ICategoryRepository {

    fun getCategories(): Observable<List<APICategory>>

    fun saveCategories(categories:ArrayList<DBCategory>)

    fun getCategory(uuid:String):DBCategory
}