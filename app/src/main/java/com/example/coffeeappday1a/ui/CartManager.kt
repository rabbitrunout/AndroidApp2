package com.example.coffeeappday1a.ui

import com.example.coffeeappday1a.data.model.CoffeeDrink

object CartManager {

    private val items = mutableListOf<CoffeeDrink>()

    fun add(item: CoffeeDrink) {
        items.add(item)
    }

    fun getItems(): List<CoffeeDrink> = items

    fun getTotalPrice(): Double = items.sumOf { it.price }

    fun clear() = items.clear()
}