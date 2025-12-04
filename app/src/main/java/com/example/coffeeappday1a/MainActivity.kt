package com.example.coffeeappday1a

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeappday1a.databinding.ActivityMainBinding
import com.example.coffeeappday1a.ui.CartFragment
import com.example.coffeeappday1a.ui.CartManager
import com.example.coffeeappday1a.ui.FavoritesFragment
import com.example.coffeeappday1a.ui.HomeFragment
import com.example.coffeeappday1a.ui.ProfileFragment
import com.google.android.material.badge.BadgeDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // стартовый экран
        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_favorites -> {
                    replaceFragment(FavoritesFragment())
                    true
                }
                R.id.nav_cart -> {
                    replaceFragment(CartFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // нарисовать бейдж при старте
        updateCartBadge()
    }

    override fun onResume() {
        super.onResume()
        // Обновляем бейдж, когда возвращаемся на MainActivity
        updateCartBadge()
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun updateCartBadge() {
        val count = CartManager.getTotalQuantity()

        val bottomNav = binding.bottomNavigation
        if (count > 0) {
            val badge = bottomNav.getOrCreateBadge(R.id.nav_cart)
            badge.isVisible = true
            badge.number = count
            badge.badgeGravity = BadgeDrawable.TOP_END
        } else {
            // если корзина пустая — убираем бейдж
            bottomNav.removeBadge(R.id.nav_cart)
        }
    }
}
