package com.example.domain.usecase

import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(private val repository: ProductRepository) {

    operator fun invoke(): Flow<List<Product>> = repository.getAllProducts()

}