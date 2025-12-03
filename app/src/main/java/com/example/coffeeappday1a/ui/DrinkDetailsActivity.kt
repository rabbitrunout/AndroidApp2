package com.example.coffeeappday1a.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeappday1a.databinding.ActivityDrinkDetailsBinding
import com.example.coffeeappday1a.data.model.CoffeeDrink
import com.example.coffeeappday1a.data.model.DrinkType
import com.example.coffeeappday1a.R

class DrinkDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrinkDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // стрелка назад
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val drink = intent.getSerializableExtra("drink") as CoffeeDrink

        binding.drinkName.text = drink.name
        binding.drinkType.text = drink.type.name
        binding.drinkSize.text = drink.size.name
        binding.drinkVolume.text = "${drink.volumeMl} ml"
        binding.drinkPrice.text = "$${drink.price}"

        binding.drinkIcon.setImageResource(
            when (drink.type) {
                DrinkType.COFFEE -> R.drawable.ic_coffee
                DrinkType.LATTE -> R.drawable.ic_latte
                DrinkType.TEA -> R.drawable.ic_tea
            }
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
