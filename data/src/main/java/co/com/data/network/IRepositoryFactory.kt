package co.com.data.network

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IRepositoryFactory {

    val mCategoriesRepository: ICategoryRepository

    val mProductRepository:IProductRepository
}