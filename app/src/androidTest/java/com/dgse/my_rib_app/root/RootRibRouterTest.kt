package com.dgse.my_rib_app.root

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RootRibRouterTest : RibTestBasePlaceholder() {

    @Mock
    internal lateinit var component: RootRibBuilder.Component
    @Mock
    internal lateinit var interactor: RootRibInteractor
    @Mock
    internal lateinit var view: RootRibView

    private var router: RootRibRouter? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        router = RootRibRouter(view, interactor, component)
    }

    /**
     * TODO: Delete this example and add real tests.
     */
    @Test
    fun anExampleTest_withSomeConditions_shouldPass() {
        // Use RouterHelper to drive your router's lifecycle.
        RouterHelper.attach(router!!)
        RouterHelper.detach(router!!)

        throw RuntimeException("Remove this test and add real tests.")
    }

}

