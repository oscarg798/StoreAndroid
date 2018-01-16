package co.com.core.use_cases.product

import co.com.core.Product
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/18/17.
 */
class GetProductsByCategoryUseCase(mSubscribeOnScheduler: Scheduler,
                                   mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<Product>, String>(mSubscribeOnScheduler, mObserverOnScheduler) {
    override fun buildUseCase(params: String): Single<List<Product>> {
        return Single.fromObservable(RepositoryFactory.instance.mProductRepository
                .getProductsByCategory(params)
                .map { apiProducts->
                    apiProducts.map {
                        Product(it.uuid, it.name, it.description, it.images,
                                it.price, it.category)
                    }
                })
    }
}
