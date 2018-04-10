package com.dgse.my_rib_app.root

import com.dgse.my_rib_app.root.intro.IntroInteractor
import com.dgse.my_rib_app.utility.view_state.RibState
import com.dgse.my_rib_app.utility.view_state.StateSelector
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {
    private val stateKeyIntro = "intro"
    private val stateKeyLogin = "login"

    @Inject
    lateinit var presenter: RootPresenter

    val stateManager = StateSelector(this.javaClass.canonicalName)

    private val stateIntro = RibState(stateKeyIntro, isDefault = true) {
        router.detachLoggedOut()
        router.attachIntro()
    }

    private val stateLogin = RibState(stateKeyLogin) {
        router.detachIntro()
        router.attachLoggedOut()
    }

    //region Lifecycle
    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        stateManager.apply {
            addState(stateIntro)
            addState(stateLogin)
            setFrom(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        val saveState = stateManager.saveTo(outState)
        super.onSaveInstanceState(saveState)
    }
    //endregion

    //region Listeners
    inner class IntroListener : IntroInteractor.Listener {
        override fun closeRequested() {
            stateManager.setBy(stateKeyLogin)
        }
    }
    //endregion

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter

}
