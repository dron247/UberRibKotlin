package com.dgse.my_rib_app.root.logged_out.number_password_entrance

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NumberPasswordEntranceRouterTest : RibTestBasePlaceholder() {

    @Mock
    internal lateinit var component: NumberPasswordEntranceBuilder.Component
    @Mock
    internal lateinit var interactor: NumberPasswordEntranceInteractor
    @Mock
    internal lateinit var view: NumberPasswordEntranceView

    private var router: NumberPasswordEntranceRouter? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        router = NumberPasswordEntranceRouter(view, interactor, component)
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

