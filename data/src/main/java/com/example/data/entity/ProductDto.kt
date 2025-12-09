package com.example.data.entity

data class ProductDto(
    val id: Int,
    val name: String,
    val imageResId: Int? = null,
    val imageUrl: String? = null,
    val ugl: Double, // Углеводы
    val category: ProductCategoryDto
)