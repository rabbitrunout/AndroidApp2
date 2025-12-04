package com.example.coffeeappday1a.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeappday1a.R
import com.example.coffeeappday1a.data.model.CoffeeDrink
import com.example.coffeeappday1a.data.model.Size
import com.example.coffeeappday1a.databinding.ActivityDrinkDetailsBinding

class DrinkDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrinkDetailsBinding
    private lateinit var drink: CoffeeDrink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drink = intent.getSerializableExtra("drink") as CoffeeDrink

        showDrink()
        setupSizeSelector()
        setupAddToCart()
    }

    private fun showDrink() {
        binding.drinkName.text = drink.name
        binding.drinkType.text = drink.type.name.lowercase().replaceFirstChar(Char::uppercase)
        binding.drinkPrice.text = "$${drink.price}"
        binding.drinkIcon.setImageResource(R.drawable.ic_coffee)
    }

    private fun setupSizeSelector() {
        binding.sizeGroup.setOnCheckedChangeListener { _, checkedId ->
            val newPrice = when (checkedId) {
                R.id.sizeSmall -> drink.price - 0.25
                R.id.sizeMedium -> drink.price
                R.id.sizeLarge -> drink.price + 0.50
                else -> drink.price
            }
            binding.drinkPrice.text = "$${String.format("%.2f", newPrice)}"
        }
    }

    private fun setupAddToCart() {
        binding.btnAddToCart.setOnClickListener {
            CartManager.add(drink)
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
        }

    }
}
