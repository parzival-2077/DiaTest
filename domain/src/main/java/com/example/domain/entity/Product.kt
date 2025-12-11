package com.example.domain.entity

data class Product(
    val id: Int,
    val name: String,
    val imageResId: Int?,
    val imageUrl: String?,
    val ugl: Double, // Углеводы
    val category: ProductCategory
)