package com.dgse.my_rib_app.root.intro

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link IntroBuilder.IntroScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class IntroRouter(
        view: IntroView,
        interactor: IntroInteractor,
        component: IntroBuilder.Component) : ViewRouter<IntroView, IntroInteractor, IntroBuilder.Component>(view, interactor, component)
