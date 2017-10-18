package co.com.data.network

import android.content.Context
import co.com.data.BASE_URL
import co.com.data.di.*


/**
 * Created by oscarg798 on 10/11/17.
 */
class RepositoryFactory private constructor() : IRepositoryFactory {

    lateinit private var mRoutesComponent: RoutesComponent



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

    override val mSessionRepository: ISessionRepository
        get() {
            val sessionRepository = SessionRepository()
            mRoutesComponent.inject(sessionRepository)
            return sessionRepository
        }

    fun injectContextAndInit(context: Context) {
        mRoutesComponent = DaggerRoutesComponent
                .builder()
                .netModule(NetModule(BASE_URL))
                .routesModule(RoutesModule())
                .databaseModule(DatabaseModule(context))
                .build()
        mRoutesComponent.inject(this)
    }

    private object Holder {
        val INSTANCE = RepositoryFactory()
    }

    companion object {
        val instance: RepositoryFactory by lazy {
            Holder.INSTANCE

        }
    }
}