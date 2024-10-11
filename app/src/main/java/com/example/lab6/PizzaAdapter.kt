package com.example.lab6

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PizzaAdapter(private val pizzaList : ArrayList<Pizza>) : RecyclerView.Adapter<PizzaAdapter.FoodViewHolder>()
{
    interface ViewHolderCheckboxListener
    {
        fun onCheckedChange (position: Int, isChecked: Boolean)
    }

    var callback: ViewHolderCheckboxListener? = null

    class FoodViewHolder(itemView: View, checkboxListener: ViewHolderCheckboxListener?): RecyclerView.ViewHolder(itemView)
    {
        // This is where we now collect our values from XML}
        val pizzaName : TextView = itemView.findViewById(R.id.tvPizzaName)
        val pizzaImage : ImageView = itemView.findViewById(R.id.ivPizzaItem)
        val pizzaCheckBox : CheckBox = itemView.findViewById(R.id.checkBoxPizzaToOrder)

        init {
            pizzaCheckBox.setOnCheckedChangeListener { checkboxView, isChecked ->
                checkboxListener?.onCheckedChange(adapterPosition, isChecked)
            }
        }
    }

    fun getPizzaByPosition(position: Int): Pizza {
        return pizzaList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.pizza_item,
            parent,false)
        return FoodViewHolder(viewLayout, callback)
    }

    override fun getItemCount(): Int {
        return pizzaList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentFood = pizzaList[position]
        holder.pizzaName.text = currentFood.pizzaName
        holder.pizzaImage.setImageResource(currentFood.pizzaImgId)
    }
}