package com.example.data.mapper

import com.example.data.entity.ProductCategoryDto
import com.example.domain.entity.ProductCategory
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

    fun mapCategoryDtoToDomain(dto: ProductCategoryDto): ProductCategory {
        return when (dto) {
            ProductCategoryDto.DRINK -> ProductCategory.DRINK
            ProductCategoryDto.MILKY -> ProductCategory.MILKY
            ProductCategoryDto.FLOUR -> ProductCategory.FLOUR
            ProductCategoryDto.FRUIT -> ProductCategory.FRUIT
            ProductCategoryDto.MEAT -> ProductCategory.MEAT
            ProductCategoryDto.VEGETABLES -> ProductCategory.VEGETABLES
            ProductCategoryDto.CANDY -> ProductCategory.CANDY
        }
    }

    fun mapDomainToCategoryDto(domain: ProductCategory): ProductCategoryDto {
        return when (domain) {
            ProductCategory.DRINK -> ProductCategoryDto.DRINK
            ProductCategory.MILKY -> ProductCategoryDto.MILKY
            ProductCategory.FLOUR -> ProductCategoryDto.FLOUR
            ProductCategory.FRUIT -> ProductCategoryDto.FRUIT
            ProductCategory.MEAT -> ProductCategoryDto.MEAT
            ProductCategory.VEGETABLES -> ProductCategoryDto.VEGETABLES
            ProductCategory.CANDY -> ProductCategoryDto.CANDY
        }
    }

}