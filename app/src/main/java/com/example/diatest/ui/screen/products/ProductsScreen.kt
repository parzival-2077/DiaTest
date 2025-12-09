package com.example.diatest.ui.screen.products

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ProductsScreen(viewModel: ProductsViewModel) {
    val products by viewModel.products.collectAsState(initial = emptyList())

    ProductScreenContent(products = products, onProductClick = { product -> })
}

