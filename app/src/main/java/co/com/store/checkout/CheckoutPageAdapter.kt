package co.com.store.checkout

import android.app.Fragment
import android.app.FragmentManager
import android.support.v13.app.FragmentPagerAdapter


/**
 * Created by oscarg798 on 11/2/17.
 */
class CheckoutPageAdapter(mFragmentManager: FragmentManager,
                          private val mFragments: ArrayList<Fragment>) : FragmentPagerAdapter(mFragmentManager) {

    override fun getItem(position: Int): Fragment = mFragments[position]

    override fun getCount(): Int = mFragments.size
}