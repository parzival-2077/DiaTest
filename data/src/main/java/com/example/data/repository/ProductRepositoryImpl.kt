package com.example.data.repository

import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl : ProductRepository {
    override fun getAllProducts(): Flow<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getAllProductInCart(): Flow<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun addProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun addProductInCard(product: Product) {
        TODO("Not yet implemented")
    }
}