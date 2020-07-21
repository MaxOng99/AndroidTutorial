package com.example.tutorial2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.Toolbar
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("option: ", "option menu")
        menuInflater.inflate(R.menu.menu_resource, menu)
        return true
    }
}