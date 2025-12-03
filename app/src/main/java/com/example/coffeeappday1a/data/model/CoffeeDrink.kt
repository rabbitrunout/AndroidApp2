package com.example.coffeeappday1a.data.model

data class CoffeeDrink(
    val id: Int,
    val name: String,
    val type: DrinkType,
    val size: Size,
    val volumeMl: Int,
    val price: Double
) : java.io.Serializable


