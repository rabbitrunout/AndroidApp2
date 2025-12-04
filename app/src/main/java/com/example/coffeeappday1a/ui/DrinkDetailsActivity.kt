package com.example.coffeeappday1a.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeappday1a.data.model.CoffeeDrink
import com.example.coffeeappday1a.data.model.Size
import com.example.coffeeappday1a.databinding.ActivityDrinkDetailsBinding

class DrinkDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrinkDetailsBinding
    private lateinit var drink: CoffeeDrink

    private var selectedSize: Size = Size.MEDIUM
    private var selectedVolume: Int = 350

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drink = intent.getSerializableExtra("drink") as CoffeeDrink

        binding.drinkName.text = drink.name
        binding.drinkType.text = drink.type.name
        binding.drinkVolume.text = "${drink.volumeMl} ml"
        binding.drinkPrice.text = String.format("$%.2f", drink.price)

        binding.radioSmall.setOnClickListener {
            selectedSize = Size.SMALL
            selectedVolume = 250
            binding.drinkVolume.text = "250 ml"
        }

        binding.radioMedium.setOnClickListener {
            selectedSize = Size.MEDIUM
            selectedVolume = 350
            binding.drinkVolume.text = "350 ml"
        }

        binding.radioLarge.setOnClickListener {
            selectedSize = Size.LARGE
            selectedVolume = 450
            binding.drinkVolume.text = "450 ml"
        }

        binding.btnAddToCart.setOnClickListener {
            val drinkForCart = drink.copy(
                size = selectedSize,
                volumeMl = selectedVolume
            )

            CartManager.add(drinkForCart)
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
        }
    }
}
