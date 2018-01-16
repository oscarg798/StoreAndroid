package co.com.core.use_cases.product

import co.com.core.Product
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/11/17.
 */
class GetProductsUseCase(mSubscribeOnScheduler: Scheduler,
                         mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<Product>, Void?>(mSubscribeOnScheduler, mObserverOnScheduler) {

    override fun buildUseCase(params: Void?): Single<List<Product>> {
        return Single.fromObservable(RepositoryFactory.instance.mProductRepository.getProducts())
                .map { products ->
                    products.map {
                        Product(it.uuid, it.name, it.description, it.images,
                                it.price, it.category)
                    }
                }
    }


}