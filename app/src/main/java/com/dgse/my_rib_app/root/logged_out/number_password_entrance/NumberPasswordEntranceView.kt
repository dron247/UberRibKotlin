package com.dgse.my_rib_app.root.logged_out.number_password_entrance

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

/**
 * Top level view for {@link NumberPasswordEntranceBuilder.NumberPasswordEntranceScope}.
 */
class NumberPasswordEntranceView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), NumberPasswordEntranceInteractor.NumberPasswordEntrancePresenter
