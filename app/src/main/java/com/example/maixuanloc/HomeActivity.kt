package com.example.maixuanloc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var usernameText: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var iconCart: ImageView

    private var productAdapter: ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        usernameText = findViewById(R.id.usernameText)
        recyclerView = findViewById(R.id.recyclerView)
        iconCart = findViewById(R.id.iconCart)

        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "Người dùng")
        usernameText.text = "Xin chào, $email!"

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        productAdapter = ProductAdapter(emptyList()) { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product_id", product.id)
            intent.putExtra("product_title", product.title)
            intent.putExtra("product_price", product.price)
            intent.putExtra("product_image", product.image)
            intent.putExtra("product_description", product.description)
            startActivity(intent)
        }
        recyclerView.adapter = productAdapter

        iconCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        getProducts()
    }

    private fun getProducts() {
        val apiService = ApiClient.retrofit.create(ApiService::class.java)
        val call = apiService.getProducts()

        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                if (response.isSuccessful) {
                    val products = response.body()
                    products?.let {
                        productAdapter?.updateData(it)
                    }
                } else {
                    println("Lỗi lấy sản phẩm: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                println("Lỗi kết nối: ${t.message}")
            }
        })
    }

    fun onLogout(view: View?) {
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
