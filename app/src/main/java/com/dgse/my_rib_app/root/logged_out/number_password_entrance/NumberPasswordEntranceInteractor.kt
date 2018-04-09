package com.dgse.my_rib_app.root.logged_out.number_password_entrance

import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [NumberPasswordEntranceScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class NumberPasswordEntranceInteractor :
        Interactor<NumberPasswordEntranceInteractor.NumberPasswordEntrancePresenter,
                NumberPasswordEntranceRouter>() {

    @Inject
    lateinit var presenter: NumberPasswordEntrancePresenter

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface NumberPasswordEntrancePresenter
}
