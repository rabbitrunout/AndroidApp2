package com.example.coffeeappday1a.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeappday1a.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CartAdapter(CartManager.getItems())
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        updateTotal()

        binding.btnClearCart.setOnClickListener {
            CartManager.clear()
            adapter.update(CartManager.getItems())
            updateTotal()
        }
    }

    private fun updateTotal() {
        val total = CartManager.getTotalPrice()
        binding.totalPriceText.text = "Total: $${String.format("%.2f", total)}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
