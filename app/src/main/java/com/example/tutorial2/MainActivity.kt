package com.example.tutorial2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.tutorial2.data.CasesDataSource
import com.example.tutorial2.models.PolicyOwner
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.simple_toolbar.*

class MainActivity : AppCompatActivity(){

    private val dataSource = CasesDataSource()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.e_doc)
        var toolbar = findViewById<Toolbar>(R.id.e_doc_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_icon)
        simple_toolbar_title.text = "eDoc"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setUpViewPager()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_resource,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_search_user -> {
            var intent = Intent(this, SearchUiActivity::class.java)
            intent.putExtra("data_source", dataSource.getCases())
            intent.putExtra("agents", dataSource.getAgents())
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
            tabLayout.getTabAt(x)?.setCustomView(R.layout.e_doc_tab_layout)
        }

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

        var caseList:ArrayList<PolicyOwner>? = null
        dataSource.populateAgent()
        dataSource.populateData()
        caseList = dataSource.getCases()

        val pageAdapter = EDocPageAdapter(this, caseList, findViewById(R.id.e_doc_search_view))
        viewPager.adapter = pageAdapter

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.setCustomView(R.layout.e_doc_tab_layout)
            var value = tab.customView?.findViewById<TextView>(R.id.value)
            var indicator = tab.customView?.findViewById<TextView>(R.id.indicator)

            when (position) {
                0 -> {
                    value?.text = caseList?.size.toString()
                    indicator?.text = "New Business"

                }
                1 -> {
                    value?.text = caseList?.size.toString()
                    indicator?.text = "Policy Serving"
                }
            }
        }.attach()
    }
}