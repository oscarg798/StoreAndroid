package co.com.store.location

import android.os.Bundle
import co.com.core.Location
import co.com.core.use_cases.location.GetLocationUseCase
import co.com.core.use_cases.location.MakeLocationFavoriteUseCase
import co.com.core.use_cases.location.RemoveLocationFromFavoriteUseCase
import co.com.data.LocalStorage
import co.com.data.TOKEN_KEY
import co.com.store.dashboard.IBaseView
import com.core.usecases.ISingleUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by oscarg798 on 10/19/17.
 */
class LocationFragmentPresenter : ILocationFragmentPresenter {

    private var mView: ILocationFragmentView? = null

    override fun bind(view: ILocationFragmentView) {
        mView = view
    }

    override fun onCreate(bundle: Bundle?) {

    }

    override fun onViewCreated() {
        mView?.initComponents()
        getLocations()
    }

    override fun makeLocationFavorite(uuid: String) {
        mView?.showProgressBar()
        val iterator: ISingleUseCase<Location, String> = MakeLocationFavoriteUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())

        iterator.execute(uuid, object : DisposableSingleObserver<Location>() {
            override fun onSuccess(t: Location) {
                mView?.updateLocationFavorite(t.mUuid, t.mFavorite)
                mView?.hideProgressBar()
                this.dispose()
            }

            override fun onError(e: Throwable) {
                mView?.hideProgressBar()
                this.dispose()
            }

        })
    }

    override fun removeLocationFromFavorite(uuid: String) {
        val iterator: ISingleUseCase<Location, String> = RemoveLocationFromFavoriteUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())

        iterator.execute(uuid, object : DisposableSingleObserver<Location>() {
            override fun onSuccess(t: Location) {
                mView?.updateLocationFavorite(t.mUuid, t.mFavorite)
                mView?.hideProgressBar()
                this.dispose()
            }

            override fun onError(e: Throwable) {
                mView?.hideProgressBar()
                this.dispose()
            }

        })
    }

    override fun onLocationClickListener(location: Location) {

    }

    private fun getLocations() {
        mView?.showProgressBar()
        val iterator: ISingleUseCase<List<Location>, String> = GetLocationUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())

        iterator.execute(LocalStorage.instance.getData(TOKEN_KEY)!!,
                object : DisposableSingleObserver<List<Location>>() {
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        mView?.hideProgressBar()
                        this.dispose()
                    }

                    override fun onSuccess(t: List<Location>) {
                        mView?.showLocations(t)
                        mView?.hideProgressBar()
                        this.dispose()
                    }

                })


    }

    override fun onDestroy() {
        mView = null
    }
}