package com.example.maixuanloc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.Button
import android.content.Intent

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var textTotalPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.recyclerViewCart)
        textTotalPrice = findViewById(R.id.textTotalPrice)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CartAdapter(CartManager.getCartItems().toMutableList()) {
            updateTotalPrice()
        }
        recyclerView.adapter = adapter
// Gắn sự kiện nút quay lại ở đây
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // đóng Activity hiện tại → quay về màn trước
        }
        // qua trang thanh toán
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)
        btnCheckout.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        // Lần đầu load tổng tiền
        updateTotalPrice()

    }

    private fun updateTotalPrice() {
        val total = CartManager.getTotalPrice()
        textTotalPrice.text = "Tổng: $${"%.2f".format(total)}"

    }
}
