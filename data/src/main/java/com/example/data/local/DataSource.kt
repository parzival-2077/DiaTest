package com.example.data.local

import com.example.data.entity.ProductCategoryDto
import com.example.data.entity.ProductDto

class DataSource {
    private val _products = mutableListOf(
        ProductDto(
            id = 1,
            name = "Добрый Cola",
            ugl = 35.0,
            category = ProductCategoryDto.DRINK
        )
//        ProductDto(
//            id = 2,
//            name = "Молоко 3.5%",
//            imageResId = R.drawable.milk,
//            ugl = 4.7,
//            category = "Напитки"
//        ),
//        Product(
//            id = 3,
//            name = "Масло сливочное",
//            imageResId = R.drawable.butter,
//            ugl = 1.4,
//            category = "Молочные продукты"
//        ),
//        Product(
//            id = 4,
//            name = "Хлеб",
//            imageResId = R.drawable.bread,
//            ugl = 12.0,
//            category = "Мучные изделия"
//        ),
//        Product(
//            id = 5,
//            name = "Шоколадка Milka",
//            imageResId = R.drawable.milka,
//            ugl = 59.0,
//            category = "Кондитерские изделия"
//        )
    )

//    fun loadProducts(): List<Product> {
//        return _products.toList()
//    }
//
//    fun addProduct(product: Product) {
//        // Генерируем новый уникальный ID
//        val newId = (_products.maxOfOrNull { it.id } ?: 0) + 1
//        _products.add(product.copy(id = newId))
//    }

//    fun getNextId(): Int {
//        return (_products.maxOfOrNull { it.id } ?: 0) + 1
//    }
}