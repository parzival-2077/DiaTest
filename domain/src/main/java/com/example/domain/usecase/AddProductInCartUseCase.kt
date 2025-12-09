package com.example.domain.usecase

import com.example.domain.entity.Product
import com.example.domain.entity.ProductCategory
import com.example.domain.repository.ProductRepository

class AddProductInCartUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(name: String, ugl: Double, category: String) {
        val product = Product(
            name = name,
            ugl = ugl,
            category = ProductCategory.valueOf(category)
        )
        repository.addProductInCard(product)
    }

}