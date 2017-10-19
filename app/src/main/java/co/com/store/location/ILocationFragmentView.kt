package co.com.store.location

import co.com.core.Location
import co.com.store.dashboard.IBaseView

/**
 * Created by oscarg798 on 10/19/17.
 */
interface ILocationFragmentView:IBaseView{

    fun showLocations(locations:List<Location>)

    fun showProgressBar()

    fun hideProgressBar()
}