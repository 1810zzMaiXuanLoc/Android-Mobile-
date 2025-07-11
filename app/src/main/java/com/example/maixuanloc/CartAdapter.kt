package com.example.maixuanloc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartAdapter(
    private var cartItems: MutableList<CartItem>,
    private val onUpdate: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProduct: ImageView = itemView.findViewById(R.id.imageProduct)
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textPrice: TextView = itemView.findViewById(R.id.textPrice)
        val textQuantity: TextView = itemView.findViewById(R.id.textQuantity)
        val btnIncrease: Button = itemView.findViewById(R.id.btnIncrease)
        val btnDecrease: Button = itemView.findViewById(R.id.btnDecrease)
        val btnRemove: Button = itemView.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.textTitle.text = item.title
        holder.textPrice.text = "$${item.price}"
        holder.textQuantity.text = item.quantity.toString()

        Glide.with(holder.itemView.context)
            .load(item.image)
            .into(holder.imageProduct)

        holder.btnIncrease.setOnClickListener {
            CartManager.increaseQuantity(item.id)
            refreshData()
        }

        holder.btnDecrease.setOnClickListener {
            CartManager.decreaseQuantity(item.id)
            refreshData()
        }

        holder.btnRemove.setOnClickListener {
            CartManager.removeItem(item.id)
            refreshData()
        }
    }

    private fun refreshData() {
        cartItems = CartManager.getCartItems().toMutableList()
        notifyDataSetChanged()
        onUpdate()
    }

    override fun getItemCount(): Int = cartItems.size
}
