package com.dgse.my_rib_app

import android.os.Bundle
import android.view.ViewGroup
import com.dgse.my_rib_app.root.RootBuilder
import com.dgse.my_rib_app.root.RootInteractor
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class RootActivity : RibActivity() {

    lateinit var rootInteractor: RootInteractor
    /**
     * Creates the [Interactor].
     *
     * @return the [Interactor].
     */
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> {
        val rootBuilder = RootBuilder(object : RootBuilder.ParentComponent {})
        val router = rootBuilder.build(parentViewGroup)
        rootInteractor = router.interactor
        return router
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
