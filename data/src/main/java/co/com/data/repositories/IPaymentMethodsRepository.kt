package co.com.data.repositories

import co.com.data.APIPaymentMethod
import io.reactivex.Observable

/**
 * Created by oscarg798 on 11/2/17.
 */
interface IPaymentMethodsRepository {


    fun getPaymentMethods(): Observable<ArrayList<APIPaymentMethod>>
}