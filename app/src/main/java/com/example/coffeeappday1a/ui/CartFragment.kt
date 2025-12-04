package com.example.coffeeappday1a.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeappday1a.MainActivity
import com.example.coffeeappday1a.R
import com.example.coffeeappday1a.databinding.FragmentCartBinding

class CartFragment : Fragment(R.layout.fragment_cart) {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCartBinding.bind(view)
        updateCart()
    }

    private fun updateCart() {
        val items = CartManager.getItems().toMutableList()

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = CartAdapter(items) {
            updateTotal()
            updateCartBadge()
        }

        updateTotal()
        updateCartBadge()
    }

    private fun updateTotal() {
        binding.cartTotalText.text = String.format("$%.2f", CartManager.getTotalPrice())
    }

    private fun updateCartBadge() {
        (activity as? MainActivity)?.updateCartBadge()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
