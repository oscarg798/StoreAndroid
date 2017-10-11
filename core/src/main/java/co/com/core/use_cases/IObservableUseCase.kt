package com.core.usecases

import io.reactivex.observers.DisposableObserver

/**
 * Created by oscarg798 on 9/20/17.
 */
interface IObservableUseCase<Response, Params>{

    fun execute(params: Params, observer: DisposableObserver<Response>)

    fun dispose()
}