package com.example.coffeeappday1a.data.repo

import com.example.coffeeappday1a.data.model.CoffeeDrink
import com.example.coffeeappday1a.data.model.DrinkType
import com.example.coffeeappday1a.data.model.Size

class CoffeeRepository {

    fun getCoffeeDrinks(): List<CoffeeDrink> {
        return listOf(
            CoffeeDrink(0, "Americano", DrinkType.COFFEE, Size.MEDIUM, 300, 2.00),
            CoffeeDrink(1, "Cappuccino", DrinkType.COFFEE, Size.MEDIUM, 350, 2.50),
            CoffeeDrink(2, "Espresso", DrinkType.COFFEE, Size.SMALL, 60, 1.50),
            CoffeeDrink(3, "Latte", DrinkType.LATTE, Size.LARGE, 450, 3.00),
            CoffeeDrink(4, "Chai Tea Latte", DrinkType.TEA, Size.MEDIUM, 400, 3.25),
            CoffeeDrink(5, "Chai Latte", DrinkType.TEA, Size.MEDIUM, 350, 2.50),
            CoffeeDrink(6, "Chai Tea", DrinkType.TEA, Size.SMALL, 250, 1.50),
            CoffeeDrink(7, "Matcha Latte", DrinkType.TEA, Size.MEDIUM, 400, 3.75),
            CoffeeDrink(8, "Caramel Macchiato", DrinkType.COFFEE, Size.LARGE, 450, 4.00)
        )
    }
}
