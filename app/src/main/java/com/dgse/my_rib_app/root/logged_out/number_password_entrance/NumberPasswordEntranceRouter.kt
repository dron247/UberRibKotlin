package com.dgse.my_rib_app.root.logged_out.number_password_entrance

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link NumberPasswordEntranceBuilder.NumberPasswordEntranceScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class NumberPasswordEntranceRouter(
        view: NumberPasswordEntranceView,
        interactor: NumberPasswordEntranceInteractor,
        component: NumberPasswordEntranceBuilder.Component) : ViewRouter<NumberPasswordEntranceView, NumberPasswordEntranceInteractor, NumberPasswordEntranceBuilder.Component>(view, interactor, component)
