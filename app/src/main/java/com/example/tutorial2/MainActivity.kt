package com.example.tutorial2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.simple_toolbar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.e_doc)
        simple_toolbar_title.text = "eDoc"
        var toolbar = findViewById<Toolbar>(R.id.e_doc_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_icon)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setUpViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("option: ", "option menu")
        menuInflater.inflate(R.menu.menu_resource, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search_user -> {
            var intent = Intent(this, SearchUiActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun setUpViewPager() {

        var viewPager = findViewById<ViewPager2>(R.id.e_doc_view_pager)
        var tabLayout = findViewById<TabLayout>(R.id.e_doc_tab_layout)
        for (x in 0..tabLayout.tabCount) {
            tabLayout.getTabAt(x)?.setCustomView(R.layout.custom_tab)
        }
        val pageAdapter = EDocPageAdapter(this)
        viewPager.adapter = pageAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                var value = tab?.customView?.findViewById<TextView>(R.id.value)
                value?.setTextColor(Color.parseColor("#E53935"))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                var value = tab?.customView?.findViewById<TextView>(R.id.value)
                value?.setTextColor(Color.parseColor("#F39999"))

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                var value = tab?.customView?.findViewById<TextView>(R.id.value)
                value?.setTextColor(Color.parseColor("#E53935"))

            }
        })

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.setCustomView(R.layout.custom_tab)
            var value = tab.customView?.findViewById<TextView>(R.id.value)
            var indicator = tab.customView?.findViewById<TextView>(R.id.indicator)

            when (position) {
                0 -> {
                    value?.text = "23"
                    indicator?.text = "New Business"
                }
                1 -> {
                    value?.text = "18"
                    indicator?.text = "Policy Serving"
                }
            }
        }.attach()
    }
}