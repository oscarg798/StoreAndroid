package co.com.data.di

import co.com.data.network.CategoryRepository
import co.com.data.network.ProductRepository
import co.com.data.network.RepositoryFactory
import co.com.data.network.SessionRepository
import dagger.Component

/**
 * Created by oscargallon on 6/2/17.
 */
@RouteScope
@Component(modules = arrayOf(RoutesModule::class))
interface RoutesComponent {

    fun inject(repositoryFactory: RepositoryFactory)

    fun inject(categoryRepository: CategoryRepository)

    fun inject(productRepository: ProductRepository)

    fun inject(sessionRepository: SessionRepository)
}