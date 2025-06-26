package com.example.maixuanloc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Gắn ID các layout sản phẩm
        val product1 = findViewById<LinearLayout>(R.id.product1)
        val product2 = findViewById<LinearLayout>(R.id.product2)
        val product3 = findViewById<LinearLayout>(R.id.product3)

        // Gắn cùng 1 hành động click để mở DetailActivity
        val goToDetail = View.OnClickListener {
            val intent = Intent(this, ProductDetailActivity::class.java)
            startActivity(intent)
        }

        product1.setOnClickListener(goToDetail)
        product2.setOnClickListener(goToDetail)
        product3.setOnClickListener(goToDetail)
    }

    fun onLogout(view: View?) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
