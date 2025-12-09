package com.example.diatest.ui.screen.add_product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diatest.R
import com.example.diatest.utils.toTitleRes
import com.example.domain.entity.ProductCategory

@Composable
internal fun AddProductScreenContent(
    productName: String,
    onProductNameChange: (String) -> Unit,
    carbohydrates: String,
    onCarbohydratesChange: (String) -> Unit,
    selectedCategory: ProductCategory?,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onCategorySelected: (ProductCategory) -> Unit,
    showError: String,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (showError.isNotEmpty()) {
            Text(
                text = showError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        OutlinedTextField(
            value = productName,
            onValueChange = onProductNameChange,
            label = { Text("Product Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = carbohydrates,
            onValueChange = onCarbohydratesChange,
            label = { Text("Carbohydrates (g)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        CategoryDropDown(
            expanded = expanded,
            selectedCategory = selectedCategory,
            onExpandedChange = {
                onExpandedChange(it)
            },
            onSelected = onCategorySelected,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryDropDown(
    expanded: Boolean,
    selectedCategory: ProductCategory?,
    onSelected: (ProductCategory) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = ProductCategory.entries

    val text = stringResource(
        id = selectedCategory?.toTitleRes() ?: R.string.select_category
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            onExpandedChange(!expanded)
        }
    ) {
        TextField(
            value = text,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                TrailingIcon(expanded = expanded)
            },
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryEditable)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                onExpandedChange(false)
            }
        ) {
            options.forEach { category ->
                DropdownMenuItem(
                    text = { Text(stringResource(category.toTitleRes())) },
                    onClick = {
                        onSelected(category)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDropdownCollapsed() {
    CategoryDropDown(
        expanded = false,
        selectedCategory = null,
        onExpandedChange = {},
        onSelected = {}
    )
}

@Preview
@Composable
private fun PreviewDropdownExpanded() {
    CategoryDropDown(
        expanded = true,
        selectedCategory = ProductCategory.DRINK,
        onExpandedChange = {},
        onSelected = {}
    )
}