package com.example.data.repository

import com.example.data.local.LocalDataSource
import com.example.data.mapper.ProductMapper
import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val productMapper: ProductMapper
) : ProductRepository {
    override fun getAllProducts(): Flow<List<Product>> =
        localDataSource.getAllProducts()
            .map { dtoList -> productMapper.mapDtoListToDomainList(dtoList) }

    override fun getAllProductInCart(): Flow<List<Product>> = localDataSource.getAllCartProducts()
        .map { dtoList -> productMapper.mapDtoListToDomainList(dtoList) }

    override suspend fun addProduct(product: Product) {
        val dto = productMapper.mapDomainToDto(product)
        localDataSource.addProduct(dto)
    }

    override suspend fun addProductInCard(product: Product) {
        val dto = productMapper.mapDomainToDto(product)
        localDataSource.addProductToCart(dto)
    }
}