package com.example.domain.usecase

import com.example.domain.entity.Product
import com.example.domain.entity.ProductCategory
import com.example.domain.repository.ProductRepository

class AddProductUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(name: String, ugl: Double, category: ProductCategory) {
        val product = Product(
            name = name,
            ugl = ugl,
            category = category
        )
        repository.addProduct(product)
    }

}