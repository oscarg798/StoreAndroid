package co.com.data.network

import co.com.data.BASE_URL
import co.com.data.di.DaggerRoutesComponent
import co.com.data.di.NetModule
import co.com.data.di.RoutesComponent
import co.com.data.di.RoutesModule


/**
 * Created by oscarg798 on 10/11/17.
 */
class RepositoryFactory private constructor() : IRepositoryFactory {

    private var mRoutesComponent: RoutesComponent = DaggerRoutesComponent
            .builder()
            .netModule(NetModule(BASE_URL))
            .routesModule(RoutesModule())
            .build()

    init {
        mRoutesComponent.inject(this)
    }

    override val mCategoriesRepository: ICategoryRepository
        get() {
            val categoryRepository = CategoryRepository()
            mRoutesComponent.inject(categoryRepository)
            return categoryRepository
        }

    override val mProductRepository: IProductRepository
        get() {
            val productRepository = ProductRepository()
            mRoutesComponent.inject(productRepository)
            return productRepository
        }

    private object Holder {
        val INSTANCE = RepositoryFactory()
    }

    companion object {
        val instance:RepositoryFactory by lazy {
            Holder.INSTANCE
        }
    }
}