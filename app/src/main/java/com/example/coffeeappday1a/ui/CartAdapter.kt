package com.example.coffeeappday1a.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeappday1a.R
import com.example.coffeeappday1a.data.model.CoffeeDrink

class CartAdapter(
    private var items: MutableList<CoffeeDrink>,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartVH>() {

    inner class CartVH(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.cartItemName)
        val qty: TextView = view.findViewById(R.id.cartQty)
        val price: TextView = view.findViewById(R.id.cartItemPrice)
        val btnPlus: Button = view.findViewById(R.id.btnPlus)
        val btnMinus: Button = view.findViewById(R.id.btnMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartVH(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CartVH, position: Int) {
        val item = items[position]

        holder.name.text = item.name
        holder.qty.text = item.quantity.toString()
        holder.price.text = String.format("$%.2f", item.price * item.quantity)

        holder.btnPlus.setOnClickListener {
            item.quantity++
            notifyItemChanged(position)
            onQuantityChanged()
        }

        holder.btnMinus.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                notifyItemChanged(position)
            } else {
                items.removeAt(position)
                notifyItemRemoved(position)
            }
            onQuantityChanged()
        }
    }

    fun update(newList: List<CoffeeDrink>) {
        items = newList.toMutableList()
        notifyDataSetChanged()
    }
}
