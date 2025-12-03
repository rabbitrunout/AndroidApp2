package com.example.coffeeappday1a.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeappday1a.R
import com.example.coffeeappday1a.data.model.CoffeeDrink
import com.example.coffeeappday1a.data.model.DrinkType
import com.example.coffeeappday1a.data.model.Size


class DrinkAdapter(
    private val onItemClick: (CoffeeDrink) -> Unit
) : ListAdapter<CoffeeDrink, DrinkAdapter.DrinkVH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<CoffeeDrink>() {
            override fun areItemsTheSame(oldItem: CoffeeDrink, newItem: CoffeeDrink) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CoffeeDrink, newItem: CoffeeDrink) =
                oldItem == newItem
        }
    }

    inner class DrinkVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val icon: ImageView = itemView.findViewById(R.id.icon)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        private val price: TextView = itemView.findViewById(R.id.price)

        fun bind(item: CoffeeDrink) {

            title.text = item.name

            val typeText = when (item.type) {
                DrinkType.COFFEE -> "Coffee"
                DrinkType.LATTE -> "Latte"
                DrinkType.TEA -> "Tea"
            }

            val sizeText = when (item.size) {
                Size.SMALL -> "Small"
                Size.MEDIUM -> "Medium"
                Size.LARGE -> "Large"
            }

            subtitle.text = "$typeText • $sizeText • ${item.volumeMl} ml"

            price.text = String.format("$%.2f", item.price)

            icon.setImageResource(
                when (item.type) {
                    DrinkType.COFFEE -> R.drawable.ic_coffee
                    DrinkType.LATTE -> R.drawable.ic_latte
                    DrinkType.TEA -> R.drawable.ic_tea
                }
            )

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DrinkDetailsActivity::class.java)
                intent.putExtra("drink", item)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_drink, parent, false)
        return DrinkVH(view)
    }

    override fun onBindViewHolder(holder: DrinkVH, position: Int) {
        holder.bind(getItem(position))
    }
}
