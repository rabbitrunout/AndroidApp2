package com.example.coffeeappday1a.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeappday1a.MainActivity
import com.example.coffeeappday1a.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CartAdapter(
            CartManager.getItems().toMutableList()
        ) {
            updateTotal()
            updateEmptyState()
            (activity as? MainActivity)?.updateCartBadge()
        }

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        val swipeHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                CartManager.removeAt(pos)
                adapter.update(CartManager.getItems())
                updateTotal()
                updateEmptyState()
                (activity as? MainActivity)?.updateCartBadge()
            }
        })

        swipeHelper.attachToRecyclerView(binding.cartRecyclerView)

        binding.btnCheckout.setOnClickListener {
            if (CartManager.getItems().isEmpty()) {
                Toast.makeText(requireContext(), "Your cart is empty", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Order placed! ☕", Toast.LENGTH_SHORT).show()
                CartManager.clear()
                adapter.update(CartManager.getItems())
                updateTotal()
                updateEmptyState()
                (activity as? MainActivity)?.updateCartBadge()
            }
        }

        updateTotal()
        updateEmptyState()
    }

    private fun updateTotal() {
        val total = CartManager.getTotalPrice()
        binding.totalPriceText.text = "Total: $${String.format("%.2f", total)}"
    }

    private fun updateEmptyState() {
        val isEmpty = CartManager.getItems().isEmpty()
        binding.tvEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.cartRecyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
        binding.btnCheckout.isEnabled = !isEmpty
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
