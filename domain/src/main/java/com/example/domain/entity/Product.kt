package com.example.domain.entity

data class Product(
    val id: Int = UNDEFINED_ID,
    val name: String,
    val imageResId: Int? = null,
    val imageUrl: String? = null,
    val ugl: Double, // Углеводы
    val category: ProductCategory
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}