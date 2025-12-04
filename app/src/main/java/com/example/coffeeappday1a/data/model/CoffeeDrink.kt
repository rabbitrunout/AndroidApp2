package com.example.coffeeappday1a.data.model

import java.io.Serializable

data class CoffeeDrink(
    val id: Int,
    val name: String,
    val type: DrinkType,
    val size: Size,
    val volumeMl: Int,
    val price: Double,
    var quantity: Int = 1   // 👈 добавили количество
) : Serializable
