package com.dgse.my_rib_app.root.logged_out

import com.dgse.my_rib_app.root.RootView
import com.dgse.my_rib_app.root.logged_out.number_password_entrance.NumberPasswordEntranceBuilder
import com.dgse.my_rib_app.root.logged_out.number_password_entrance.NumberPasswordEntranceRouter
import com.uber.rib.core.Router

/**
 * Adds and removes children of {@link LoggedOutBuilder.LoggedOutScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LoggedOutRouter(
        interactor: LoggedOutInteractor,
        component: LoggedOutBuilder.Component,
        private val rootView: RootView,
        private val numberPasswordEntranceBuilder: NumberPasswordEntranceBuilder
) : Router<LoggedOutInteractor, LoggedOutBuilder.Component>(interactor, component) {

    private var numberPasswordEntranceRouter: NumberPasswordEntranceRouter? = null

    fun attachNumberPasswordEntrance() {
        if (numberPasswordEntranceRouter == null) {
            numberPasswordEntranceBuilder.build(rootView).let {
                numberPasswordEntranceRouter = it
                rootView.addView(it.view)
            }
        }
    }
}
