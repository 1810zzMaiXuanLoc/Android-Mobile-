package com.example.maixuanloc

data class CartResponse(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<CartProduct>
)
