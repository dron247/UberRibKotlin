package com.dgse.my_rib_app.root

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RootRibInteractorTest : RibTestBasePlaceholder() {

    @Mock
    internal lateinit var presenter: RootRibInteractor.RootRibPresenter
    @Mock
    internal lateinit var router: RootRibRouter

    private var interactor: RootRibInteractor? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        interactor = TestRootRibInteractor.create(presenter)
    }

    /**
     * TODO: Delete this example and add real tests.
     */
    @Test
    fun anExampleTest_withSomeConditions_shouldPass() {
        // Use InteractorHelper to drive your interactor's lifecycle.
        InteractorHelper.attach<RootRibInteractor.RootRibPresenter, RootRibRouter>(interactor!!, presenter, router, null)
        InteractorHelper.detach(interactor!!)

        throw RuntimeException("Remove this test and add real tests.")
    }
}