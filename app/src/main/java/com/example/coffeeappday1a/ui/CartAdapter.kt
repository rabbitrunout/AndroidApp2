package com.example.coffeeappday1a.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeappday1a.R
import com.example.coffeeappday1a.data.model.CoffeeDrink

class CartAdapter(
    private var items: List<CoffeeDrink>
) : RecyclerView.Adapter<CartAdapter.CartVH>() {

    inner class CartVH(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.cartItemName)
        val price: TextView = view.findViewById(R.id.cartItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_drink, parent, false)
        return CartVH(view)
    }

    override fun onBindViewHolder(holder: CartVH, position: Int) {
        val drink = items[position]
        holder.name.text = drink.name
        holder.price.text = "$${String.format("%.2f", drink.price)}"
    }

    override fun getItemCount() = items.size

    fun update(newItems: List<CoffeeDrink>) {
        items = newItems
        notifyDataSetChanged()
    }
}
