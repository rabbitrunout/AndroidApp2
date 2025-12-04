package com.example.coffeeappday1a.ui

import com.example.coffeeappday1a.data.model.CoffeeDrink

object CartManager {

    private val items = mutableListOf<CoffeeDrink>()

    fun add(item: CoffeeDrink) {
        val existing = items.find { it.id == item.id }
        if (existing != null) {
            existing.quantity++
        } else {
            items.add(item.copy(quantity = 1))
        }
    }

    fun getItems(): List<CoffeeDrink> = items

    fun getTotalPrice(): Double =
        items.sumOf { it.price * it.quantity }

    fun removeAt(index: Int) {
        if (index in items.indices) {
            items.removeAt(index)
        }
    }

    fun clear() = items.clear()
}
