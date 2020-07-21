package com.example.tutorial2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.simple_toolbar))
        setContentView(R.layout.e_doc)
        supportActionBar?.title = "edoc"
    }
}