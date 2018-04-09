package com.dgse.my_rib_app.root.intro

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [IntroScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class IntroInteractor : Interactor<IntroInteractor.IntroPresenter, IntroRouter>() {

    @Inject
    lateinit var presenter: IntroPresenter

    @Inject
    lateinit var listener: Listener

    private val disposer = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        disposer.add(
                presenter.closeClicks()
                        .take(1) // we are interested only at first "close" click
                        .doOnNext { listener.closeRequested() }
                        .subscribe()
        )
    }

    override fun willResignActive() {
        disposer.dispose()
        super.willResignActive()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface IntroPresenter {
        fun closeClicks(): Observable<Any>
    }

    interface Listener {
        fun closeRequested()
    }
}
