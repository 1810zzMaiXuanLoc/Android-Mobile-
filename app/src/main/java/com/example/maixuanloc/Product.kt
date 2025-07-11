package com.example.maixuanloc

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String      // phải là String, không được Int
)
