package com.example.tutorial2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorial2.data.CasesDateSource
import com.example.tutorial2.models.Case
import kotlinx.android.synthetic.main.e_doc.*
import kotlinx.android.synthetic.main.simple_toolbar.*

class MainActivity : AppCompatActivity(), CaseRecyclerAdaptor.OnCaseListener {

    private lateinit var caseAdapter:CaseRecyclerAdaptor
    private lateinit var cases:ArrayList<Case>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.e_doc)
        simple_toolbar_title.text = "eDoc"
        var toolbar = findViewById<Toolbar>(R.id.e_doc_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_icon)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initRecyclerView()
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
            Log.d("ok", "ok")
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
    private fun initRecyclerView() {
        case_recycler_view.apply {
            val topSpacingItemDecoration = SpacingItemDecoration(30)
            var dataSource = CasesDateSource()
            dataSource.populateData()
            cases = dataSource.getCases()
            addItemDecoration(topSpacingItemDecoration)
            layoutManager = LinearLayoutManager(this@MainActivity)
            caseAdapter = CaseRecyclerAdaptor(cases, this@MainActivity)
            adapter = caseAdapter
        }
    }

    override fun onCaseClick(position: Int) {

    }

}