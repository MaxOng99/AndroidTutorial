package com.example.tutorial2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.tutorial2.models.PolicyOwner
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CaseUiActivity : AppCompatActivity() {

    private lateinit var selectedPolicyOwner:PolicyOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.case_ui)
        var toolbar = findViewById<Toolbar>(R.id.case_ui_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_icon)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setUpViewPager()
    }


    private fun setUpViewPager() {
        var viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val pageAdapter = CaseUiPageAdapter(this)
        viewPager.adapter = pageAdapter
        var tabLayout = findViewById<TabLayout>(R.id.tab_layout)

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            when (position) {
                0 -> tab.text = "Case Details"
                1 -> tab.text = "Scan Doc"
                else -> tab.text = "Info"
            }
        }.attach()
    }
}