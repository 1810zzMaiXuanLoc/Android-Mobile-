package com.example.maixuanloc

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.example.maixuanloc.Product
import com.example.maixuanloc.CartProduct
import com.example.maixuanloc.CartRequest
import com.example.maixuanloc.CartResponse
import com.example.maixuanloc.ApiClient
import com.example.maixuanloc.ApiService

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var imageProduct: ImageView
    private lateinit var textTitle: TextView
    private lateinit var textPrice: TextView
    private lateinit var textDescription: TextView
    private lateinit var btnAddToCart: Button

    private var currentProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        imageProduct = findViewById(R.id.imageProduct)
        textTitle = findViewById(R.id.textTitle)
        textPrice = findViewById(R.id.textPrice)
        textDescription = findViewById(R.id.textDescription)
        btnAddToCart = findViewById(R.id.btnAddToCart)

        val productId = intent.getIntExtra("product_id", -1)
        val title = intent.getStringExtra("product_title")
        val price = intent.getDoubleExtra("product_price", 0.0)
        val image = intent.getStringExtra("product_image")
        val description = intent.getStringExtra("product_description")
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // đóng Activity hiện tại → quay về màn trước
        }

        // Hiển thị thông tin sản phẩm
        textTitle.text = title ?: ""
        textPrice.text = "$$price"
        textDescription.text = description ?: ""
        Glide.with(this).load(image).into(imageProduct)

        currentProduct = Product(
            id = productId,
            title = title ?: "",
            price = price,
            description = description ?: "",
            image = image ?: ""
        )

        btnAddToCart.setOnClickListener {
            currentProduct?.let { product ->
                val cartItem = CartItem(
                    id = product.id,
                    title = product.title,
                    price = product.price,
                    image = product.image,
                    quantity = 1
                )
                CartManager.addToCart(cartItem)
                Toast.makeText(this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun addToCart(productId: Int) {
        val apiService = ApiClient.retrofit.create(ApiService::class.java)

        val cartProduct = CartProduct(productId = productId, quantity = 1)
        val cartRequest = CartRequest(
            userId = 1,
            date = "2025-07-10",
            products = listOf(cartProduct)
        )

        apiService.addToCart(cartRequest).enqueue(object : Callback<CartResponse> {
            override fun onResponse(
                call: Call<CartResponse>,
                response: Response<CartResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@ProductDetailActivity,
                        "Đã thêm vào giỏ hàng!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@ProductDetailActivity,
                        "Thêm giỏ hàng thất bại: ${response.errorBody()?.string()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                Toast.makeText(
                    this@ProductDetailActivity,
                    "Lỗi kết nối: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
