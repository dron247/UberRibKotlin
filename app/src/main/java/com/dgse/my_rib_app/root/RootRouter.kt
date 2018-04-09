package com.dgse.my_rib_app.root

import com.dgse.my_rib_app.root.intro.IntroBuilder
import com.dgse.my_rib_app.root.intro.IntroRouter
import com.dgse.my_rib_app.root.logged_out.LoggedOutBuilder
import com.dgse.my_rib_app.root.logged_out.LoggedOutRouter
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
        view: RootView,
        interactor: RootInteractor,
        component: RootBuilder.Component,
        private val loggedOutBuilder: LoggedOutBuilder,
        private val introBuilder: IntroBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {
    var loggedOutRouter: LoggedOutRouter? = null
    var introRouter: IntroRouter? = null

    fun attachLoggedOut() {
        loggedOutBuilder.build().let {
            loggedOutRouter = it
            attachChild(it)
        }
    }

    fun detachLoggedOut() {
        loggedOutRouter?.let { detachChild(it) }
        loggedOutRouter = null
    }

    fun attachLoggedIn() {
        //
    }

    fun detachLoggedIn() {
        //
    }

    fun attachIntro() {
        introBuilder.build(view).let {
            introRouter = it
            attachChild(it)
            view.addView(it.view)
        }
    }

    fun detachIntro() {
        introRouter?.let {
            detachChild(it)
            view.removeView(it.view)
        }
        introRouter = null
    }
}
