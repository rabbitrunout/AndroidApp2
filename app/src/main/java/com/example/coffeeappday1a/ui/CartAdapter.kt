package com.example.coffeeappday1a.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeappday1a.data.model.CoffeeDrink
import com.example.coffeeappday1a.databinding.ItemCartBinding

class CartAdapter(
    private val items: MutableList<CoffeeDrink>,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        holder.binding.cartItemName.text = item.name

        // added volume
        holder.binding.cartVolume.text = "${item.size.name} • ${item.volumeMl} ml"

        // quantity
        holder.binding.cartQty.text = item.quantity.toString()

        // price
        holder.binding.cartItemPrice.text =
            String.format("$%.2f", item.price * item.quantity)

        holder.binding.btnPlus.setOnClickListener {
            item.quantity++
            notifyItemChanged(position)
            onQuantityChanged()
        }

        holder.binding.btnMinus.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                notifyItemChanged(position)
            } else {
                val removed = items[position]
                CartManager.remove(removed)
                items.removeAt(position)
                notifyItemRemoved(position)
            }
            onQuantityChanged()
        }
    }
}
