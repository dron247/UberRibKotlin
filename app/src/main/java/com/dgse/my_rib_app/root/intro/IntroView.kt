package com.dgse.my_rib_app.root.intro

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.FrameLayout
import com.dgse.my_rib_app.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable

/**
 * Top level view for {@link IntroBuilder.IntroScope}.
 */
class IntroView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), IntroInteractor.IntroPresenter {

    lateinit var viewPager: ViewPager

    override fun onFinishInflate() {
        super.onFinishInflate()
        viewPager = findViewById(R.id.intro_pager)
        viewPager.adapter = IntroViewPagerAdapter(context)
        viewPager.currentItem = 0
        //note: add an observable to viewPager via rxBinding and
        //propagate it through presenter interface if you interested in page changes
        //could be useful for metrics
    }

    //region Rib - View implementation
    /**
     * Returns an observable that allows to observe "Close" button clicks
     */
    override fun closeClicks(): Observable<Any> {
        return RxView.clicks(findViewById(R.id.intro_btn_close))
    }
    //endregion
}
