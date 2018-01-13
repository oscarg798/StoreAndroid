package co.com.store.products

import co.com.store.dashboard.IBasePresenter

/**
 * Created by oscarg798 on 10/18/17.
 */
interface IProductFragmentPresenter : IBasePresenter<IProductFragmentView>,
        IOpenProductDetailCallback {

    fun getProducts()

    fun onViewCreated()


}