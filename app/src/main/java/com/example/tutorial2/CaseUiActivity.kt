package com.example.tutorial2

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.tutorial2.models.PolicyOwner
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.case_ui.*
import kotlinx.android.synthetic.main.simple_toolbar.*

class CaseUiActivity : AppCompatActivity() {

    private var selectedPolicyOwner:PolicyOwner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.case_ui)
        var toolbar = findViewById<Toolbar>(R.id.case_ui_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_icon)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        selectedPolicyOwner = intent?.getParcelableExtra("selected_policy_owner")
        name.text = selectedPolicyOwner?.name
        simple_toolbar_title.text = selectedPolicyOwner?.policyNo
        setUpViewPager()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun setUpViewPager() {
        var viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val pageAdapter = CaseUiPageAdapter(this)
        viewPager.adapter = pageAdapter
        var tabLayout = findViewById<TabLayout>(R.id.tab_layout)

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->

            when (position) {
                0 -> {
                    tab.text = "Case Details"
                }
                1 -> {
                    tab.text = "Scan Doc"
                }
                else -> {
                    tab.text = "Info"
                }
            }
        }.attach()
    }
}