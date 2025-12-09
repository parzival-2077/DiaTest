package com.example.data.local

import com.example.data.entity.ProductCategoryDto
import com.example.data.entity.ProductDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor() {
    private val _products = MutableStateFlow(
        listOf(
            ProductDto(
                id = 1,
                name = "Добрый Cola",
                ugl = 35.0,
                category = ProductCategoryDto.DRINK,
            )
        )
    )

    private val _cartProducts = MutableStateFlow<List<ProductDto>>(emptyList())

    fun getAllProducts(): Flow<List<ProductDto>> = _products.asStateFlow()

    fun addProduct(product: ProductDto) {
        val newId = (_products.value.maxOfOrNull { it.id } ?: 0) + 1
        _products.value += product.copy(id = newId)
    }

    fun getAllCartProducts(): Flow<List<ProductDto>> = _cartProducts.asStateFlow()

    fun addProductToCart(product: ProductDto) {
        _cartProducts.value += product
    }
}