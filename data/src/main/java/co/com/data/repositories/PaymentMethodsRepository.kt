package co.com.data.repositories

import co.com.data.APIPaymentMethod
import io.reactivex.Observable

/**
 * Created by oscarg798 on 11/2/17.
 */
class PaymentMethodsRepository : IPaymentMethodsRepository {

    override fun getPaymentMethods(): Observable<ArrayList<APIPaymentMethod>> {
        return Observable.create<ArrayList<APIPaymentMethod>> { emitter ->
            emitter.onNext(arrayListOf(APIPaymentMethod("Cash", "Cash", "", null)))
            emitter.onComplete()
        }
    }
}