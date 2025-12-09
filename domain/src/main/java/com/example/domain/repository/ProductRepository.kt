package com.example.domain.repository

import com.example.domain.entity.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<List<Product>>

    fun getAllProductInCart(): Flow<List<Product>>

    suspend fun addProduct(product: Product)

    suspend fun addProductInCard(product: Product)

}