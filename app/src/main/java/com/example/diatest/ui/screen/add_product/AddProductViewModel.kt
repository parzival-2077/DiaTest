package com.example.diatest.ui.screen.add_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.ProductCategory
import com.example.domain.usecase.AddProductUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    fun addProduct(
        name: String,
        category: ProductCategory,
        carbohydrates: Double
    ) {
        viewModelScope.launch {
            addProductUseCase(
                name = name,
                category = category,
                ugl = carbohydrates
            )
        }
    }

}