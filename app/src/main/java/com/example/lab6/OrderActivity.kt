package com.example.lab6

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class OrderActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pizzaToOrder = intent.getStringArrayExtra("orderedPizza")!!.filterNotNull().toTypedArray()
        val lvOrderedPizza = findViewById<ListView>(R.id.listViewOrderedPizza)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            pizzaToOrder
        )

        lvOrderedPizza.adapter = adapter
    }

    fun buttonBackToMainActivityClick(view: View) {
        finish()
    }
}