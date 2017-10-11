package co.com.store.dashboard

/**
 * Created by oscarg798 on 10/11/17.
 */
interface IBasePresenter{

    fun bind(view:IBaseView)

    fun onCreate()

    fun onDestroy()

}