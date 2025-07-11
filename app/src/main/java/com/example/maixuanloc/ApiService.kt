package com.example.maixuanloc

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("products")
    fun getProducts(): Call<List<Product>>  // ← BỔ SUNG HÀM NÀY
    @POST("carts")
    fun addToCart(@Body cartRequest: CartRequest): Call<CartResponse>
}
