package com.example.diatest.ui.screen.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diatest.ui.component.ProductCard
import com.example.diatest.ui.component.SearchBar
import com.example.diatest.ui.theme.DiaTestTheme
import com.example.domain.entity.Product
import com.example.domain.entity.ProductCategory

@Composable
internal fun ProductScreenContent(
    products: List<Product>,
    onProductClick: ((Product) -> Unit)
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        SearchBar()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = products, key = { it.id }) {
                ProductCard(
                    product = it,
                    onProductClick = onProductClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProductScreenContentPreview() {
    DiaTestTheme {
        ProductScreenContent(
            products = listOf(
                Product(
                    id = 1,
                    name = "Product 1",
                    ugl = 10.0,
                    category = ProductCategory.MEAT
                ),
                Product(
                    id = 2,
                    name = "Product 2",
                    ugl = 20.0,
                    category = ProductCategory.CANDY
                ),
                Product(
                    id = 3,
                    name = "Product 3",
                    ugl = 30.0,
                    category = ProductCategory.FRUIT
                )
            ),
            onProductClick = {}
        )
    }
}