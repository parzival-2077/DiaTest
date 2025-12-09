package com.example.data.mapper

import com.example.data.entity.ProductDto
import com.example.domain.entity.Product
import javax.inject.Inject

class ProductMapper @Inject constructor(
    private val categoryMapper: CategoryMapper
) {

    fun mapDtoToDomain(dto: ProductDto): Product = Product(
        id = dto.id,
        name = dto.name,
        imageResId = dto.imageResId,
        imageUrl = dto.imageUrl,
        ugl = dto.ugl,
        category = categoryMapper.mapCategoryDtoToDomain(dto.category)
    )

    fun mapDtoListToDomainList(dtoList: List<ProductDto>): List<Product> =
        dtoList.map { mapDtoToDomain(it) }

    fun mapDomainToDto(domain: Product): ProductDto = ProductDto(
        id = domain.id,
        name = domain.name,
        imageResId = domain.imageResId,
        imageUrl = domain.imageUrl,
        ugl = domain.ugl,
        category = categoryMapper.mapDomainToCategoryDto(domain.category)
    )
}