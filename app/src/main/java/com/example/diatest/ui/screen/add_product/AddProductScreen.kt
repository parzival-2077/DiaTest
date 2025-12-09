package com.example.diatest.ui.screen.add_product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.domain.entity.ProductCategory

@Composable
internal fun AddProductScreen(
    viewModel: AddProductViewModel,
    onSuccess: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
    var productNameState by remember { mutableStateOf("") }
    var carbsState by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf("") }
    var isSaved by remember { mutableStateOf(false) }

    val onProductNameChange: (String) -> Unit = { productNameState = it }
    val onCarbohydratesChange: (String) -> Unit = { carbsState = it }
    val onExpandedChange: (Boolean) -> Unit = { expanded = it }
    val onCategorySelected: (ProductCategory) -> Unit = {
        selectedCategory = it
        expanded = false
    }
    val onSaveClick: () -> Unit = {
        when {
            productNameState.isBlank() -> {
                showError = "Пожалуйста, введите название продукта"
            }

            carbsState.isBlank() -> {
                showError = "Пожалуйста, введите углеводы"
            }

            selectedCategory == null -> {
                showError = "Пожалуйста, выберите категорию"
            }

            else -> {
                try {
                    val category = selectedCategory
                    if (category != null) {
                        viewModel.addProduct(
                            name = productNameState,
                            category = category,
                            carbohydrates = carbsState.toDouble()
                        )
                        isSaved = true
                    }
                } catch (e: Exception) {
                    showError = "Ошибка при сохранении: ${e.message}"
                }
            }
        }
    }

    LaunchedEffect(isSaved) {
        if (isSaved) {
            onSuccess()
        }
    }

    AddProductScreenContent(
        productName = productNameState,
        onProductNameChange = onProductNameChange,
        carbohydrates = carbsState,
        onCarbohydratesChange = onCarbohydratesChange,
        selectedCategory = selectedCategory,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        onCategorySelected = onCategorySelected,
        showError = showError,
        onSave = onSaveClick
    )

}

