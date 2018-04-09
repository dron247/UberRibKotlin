package com.dgse.my_rib_app.root

import com.dgse.my_rib_app.root.intro.IntroInteractor
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

    @Inject
    lateinit var presenter: RootPresenter

    private var state: NodeState? = null
        set(value) {
            field = value
            field?.switchState(this)
        }

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        state = NodeState.of(savedInstanceState)
    }

    inner class IntroListener : IntroInteractor.Listener {
        override fun closeRequested() {
            state = NodeState.NumberPasswordState()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        val saveState = outState ?: Bundle()
        saveState.putString(NodeState.stateKey, state?.id)
        super.onSaveInstanceState(saveState)
    }


    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter

    private sealed class NodeState {
        abstract fun switchState(rootInteractor: RootInteractor)
        abstract val id: String

        companion object {
            const val stateKey = "com.dgse.mybeelinerib.root.CurrentState"

            fun of(savedInstanceState: Bundle?): NodeState {
                return savedInstanceState?.let {
                    val key = it.getString(NodeState.stateKey)
                    when (key) {
                        IntroState.key -> return@let IntroState()
                        NumberPasswordState.key -> return@let NumberPasswordState()
                        else -> return@let IntroState()
                    }
                } ?: IntroState()
            }
        }


        class IntroState : NodeState() {
            companion object {
                const val key = "intro"
            }

            override val id = IntroState.key

            override fun switchState(rootInteractor: RootInteractor) {
                rootInteractor.router.detachLoggedOut()
                rootInteractor.router.attachIntro()
            }
        }

        class NumberPasswordState : NodeState() {
            companion object {
                const val key = "login"
            }

            override val id = NumberPasswordState.key

            override fun switchState(rootInteractor: RootInteractor) {
                rootInteractor.router.detachIntro()
                rootInteractor.router.attachLoggedOut()
            }
        }
    }

}
