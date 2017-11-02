package co.com.core.use_cases

import co.com.core.PaymentMethod
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 11/2/17.
 */
class GetPaymentMethodsUseCase(mSubscribeOnScheduler: Scheduler,
                               mObserverOnScheduler: Scheduler) :
        SingleUseCase<ArrayList<PaymentMethod>, Any?>(mSubscribeOnScheduler, mObserverOnScheduler) {

    override fun buildUseCase(params: Any?): Single<ArrayList<PaymentMethod>> {
        return Single.fromObservable(RepositoryFactory.instance
                .mPaymentMethodsRepository.getPaymentMethods())
                .map { apiPaymentMethods ->
                    val paymentMethods = apiPaymentMethods.map {
                        PaymentMethod(it.type, it.name, it.icon, it.options)
                    }
                    ArrayList(paymentMethods)

                }
    }
}