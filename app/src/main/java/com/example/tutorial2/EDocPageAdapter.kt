package com.example.tutorial2

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tutorial2.data.CasesDataSource

class EDocPageAdapter(fa: FragmentActivity, private val data:CasesDataSource) : FragmentStateAdapter(fa) {

    private val numOfTabs = 2
    override fun getItemCount(): Int {
        return numOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewBusinessFragment(data)
            else -> PolicyServingFragment(data)
        }
    }
}