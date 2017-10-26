package co.com.store.location

import co.com.store.dashboard.IBasePresenter

/**
 * Created by oscarg798 on 10/19/17.
 */
interface ILocationFragmentPresenter:IBasePresenter, LocationAdapterCallbacks{

    fun onViewCreated()


}