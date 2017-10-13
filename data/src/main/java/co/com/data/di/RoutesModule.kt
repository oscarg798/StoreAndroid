package co.com.data.di

import co.com.data.network.ICategoryRoute
import co.com.data.network.IProductRoute
import co.com.data.network.ISessionRoute

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by oscargallon on 6/2/17.
 */
@RouteScope
@Module(includes = arrayOf(NetModule::class))
class RoutesModule {

    @Provides
    fun provideCategoryRoute(retrofit: Retrofit): ICategoryRoute = retrofit.create(ICategoryRoute::class.java)

    @Provides
    fun provideProductRoute(retrofit: Retrofit):IProductRoute = retrofit.create(IProductRoute::class.java)

    @Provides
    fun provideSessionRoure(retrofit: Retrofit):ISessionRoute = retrofit.create(ISessionRoute::class.java)


}