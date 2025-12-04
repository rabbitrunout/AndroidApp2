package com.example.coffeeappday1a.ui

import com.example.coffeeappday1a.data.model.CoffeeDrink

object CartManager {

    private val items = mutableListOf<CoffeeDrink>()

    fun add(item: CoffeeDrink) {
        items.add(item)
    }

    fun remove(item: CoffeeDrink) {
        items.remove(item)
    }

    fun getItems(): List<CoffeeDrink> = items

    fun getTotalPrice(): Double =
        items.sumOf { it.price * it.quantity }

    fun getTotalQuantity(): Int =
        items.sumOf { it.quantity }

    fun clear() = items.clear()
}
