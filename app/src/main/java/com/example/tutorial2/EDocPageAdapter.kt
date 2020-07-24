package com.example.tutorial2

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter

class EDocPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val numOfTabs = 2
    override fun getItemCount(): Int {
        return numOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewBusinessFragment()
            else -> PolicyServingFragment()
        }
    }
}