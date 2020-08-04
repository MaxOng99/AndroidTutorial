package com.example.tutorial2

import android.widget.SearchView
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tutorial2.models.PolicyOwner

class EDocPageAdapter(fa: FragmentActivity, private val data: ArrayList<PolicyOwner>?, private val searchView: SearchView) : FragmentStateAdapter(fa) {

    private val numOfTabs = 2
    override fun getItemCount(): Int {
        return numOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewBusinessFragment(ArrayList(data), searchView)
            else -> PolicyServingFragment(ArrayList(data), searchView)
        }
    }
}