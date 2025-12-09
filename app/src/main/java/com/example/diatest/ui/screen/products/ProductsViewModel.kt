package com.example.diatest.ui.screen.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.AddProductInCartUseCase
import com.example.domain.usecase.GetAllProductsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val addProductInCartUseCase: AddProductInCartUseCase
) : ViewModel() {

    val products = getAllProductsUseCase()

    fun addProduct(
        name: String,
        ugl: Double,
        category: String
    ) {
        viewModelScope.launch {
            addProductInCartUseCase(
                name = name,
                ugl = ugl,
                category = category
            )
        }
    }
}