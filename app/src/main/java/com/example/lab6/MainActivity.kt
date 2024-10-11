package com.example.lab6

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var pizzaList : ArrayList<Pizza>
    private lateinit var selectedPizzaList : ArrayList<Pizza>
    private lateinit var pizzaAdapter : PizzaAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        pizzaList = ArrayList()
        selectedPizzaList = ArrayList()
        setListOfPizza()

        //Створення адаптеру
        pizzaAdapter = PizzaAdapter(pizzaList)

        //Налаштування RecyclerView
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.myRecyclerView.setHasFixedSize(true)
        binding.myRecyclerView.adapter = pizzaAdapter

        //Обробка зміни стану чекбоксу
        pizzaAdapter.callback = object : PizzaAdapter.ViewHolderCheckboxListener
        {
            override fun onCheckedChange(position: Int, isChecked: Boolean) {
                if (isChecked)
                    selectedPizzaList.add(pizzaAdapter.getPizzaByPosition(position))
                else
                    selectedPizzaList.remove(pizzaAdapter.getPizzaByPosition(position))
            }
        }

        //Заповнення спінеру
        val pizzaNamesArray: Array<String> = pizzaList.map { it.pizzaName }.toTypedArray()
        val spinner: Spinner = findViewById(R.id.mySpinner)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pizzaNamesArray)
        spinner.setAdapter(adapter)
        spinner.adapter = adapter

    }

    private fun setListOfPizza()
    {
        pizzaList.add(Pizza("Pizza 1", R.drawable.pizza_1))
        pizzaList.add(Pizza("Pizza 2", R.drawable.pizza_2))
        pizzaList.add(Pizza("Pizza 3", R.drawable.pizza_3))
        pizzaList.add(Pizza("Pizza 4", R.drawable.pizza_4))
        pizzaList.add(Pizza("Pizza 5", R.drawable.pizza_5))
        pizzaList.add(Pizza("Pizza 6", R.drawable.pizza_6))
    }

    fun buttonOrderClick(view: View)
    {
        if (selectedPizzaList.size > 0)
        {
            val switchActivityIntent = Intent(
                this,
                OrderActivity::class.java
            )

            val bundle = Bundle()
            val pizzaNamesArray: Array<String> = selectedPizzaList.map { it.pizzaName }.toTypedArray()
            bundle.putStringArray("orderedPizza", pizzaNamesArray)

            switchActivityIntent.putExtras(bundle)
            startActivity(switchActivityIntent)
        }
    }

    fun buttonDemoScrollViewClick(view: View)
    {
        val switchActivityIntent = Intent(
            this,
            ScrollViewDemoActivity::class.java
        )
        startActivity(switchActivityIntent)
    }
}