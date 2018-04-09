package com.dgse.my_rib_app.root.intro

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dgse.my_rib_app.R

class IntroViewPagerAdapter(private val context: Context) : PagerAdapter() {
    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by [.instantiateItem]. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view Page View to check for association with `object`
     * @param object Object to check for association with `view`
     * @return true if `view` is associated with the key object `object`
     */
    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int = 2

    override fun instantiateItem(container: ViewGroup, position: Int): Any = with(context) {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(when (position) {
            0 -> R.layout.intro_page1
            else -> R.layout.intro_page2
        }, container, false)

        container.addView(view)
        return@with view
    }
}