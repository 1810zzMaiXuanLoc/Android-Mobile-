package com.example.maixuanloc

data class CartRequest(
    val userId: Int,
    val date: String,
    val products: List<CartProduct>
)

data class CartProduct(
    val productId: Int,
    val quantity: Int
)
