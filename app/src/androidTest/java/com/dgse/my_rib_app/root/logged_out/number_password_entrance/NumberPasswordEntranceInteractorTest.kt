package com.dgse.my_rib_app.root.logged_out.number_password_entrance

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NumberPasswordEntranceInteractorTest : RibTestBasePlaceholder() {

    @Mock
    internal lateinit var presenter: NumberPasswordEntranceInteractor.NumberPasswordEntrancePresenter
    @Mock
    internal lateinit var router: NumberPasswordEntranceRouter

    private var interactor: NumberPasswordEntranceInteractor? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        interactor = TestNumberPasswordEntranceInteractor.create(presenter)
    }

    /**
     * TODO: Delete this example and add real tests.
     */
    @Test
    fun anExampleTest_withSomeConditions_shouldPass() {
        // Use InteractorHelper to drive your interactor's lifecycle.
        InteractorHelper.attach<NumberPasswordEntranceInteractor.NumberPasswordEntrancePresenter, NumberPasswordEntranceRouter>(interactor!!, presenter, router, null)
        InteractorHelper.detach(interactor!!)

        throw RuntimeException("Remove this test and add real tests.")
    }
}