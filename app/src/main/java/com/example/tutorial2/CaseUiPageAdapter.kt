package com.example.tutorial2

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter

class CaseUiPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val numOfTabs = 3
    override fun getItemCount(): Int {
        return numOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CaseDetailFragment()
            1 -> ScanDocFragment()
            else -> InfoFragment()
        }
    }
}