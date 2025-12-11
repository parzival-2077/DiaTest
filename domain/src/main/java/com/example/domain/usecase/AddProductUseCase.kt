package com.example.domain.usecase

import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository

class AddProductUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(product: Product) = repository.addProduct(product)

}