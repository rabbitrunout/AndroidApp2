package com.example.coffeeappday1a.data.model

import java.io.Serializable

data class CoffeeDrink(
    val id: Long,
    val name: String,
    val type: DrinkType,
    val size: Size = Size.MEDIUM,
    val volumeMl: Int = 300,
    val price: Double
) : Serializable
