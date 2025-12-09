package com.example.diatest.model

data class CartItem(
    val productId: Int,
    val name: String,
    val imageResId: Int,
    val carbohydrates: Double,
    val category: String,
    val quantity: Int // количество в граммах
)
