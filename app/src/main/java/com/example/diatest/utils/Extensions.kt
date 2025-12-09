package com.example.diatest.utils

import androidx.annotation.StringRes
import com.example.diatest.R
import com.example.domain.entity.ProductCategory

@StringRes
fun ProductCategory.toTitleRes(): Int = when (this) {
    ProductCategory.DRINK -> R.string.drink
    ProductCategory.MILKY -> R.string.milky
    ProductCategory.FLOUR -> R.string.flour
    ProductCategory.FRUIT -> R.string.fruit
    ProductCategory.MEAT -> R.string.meat
    ProductCategory.VEGETABLES -> R.string.vegetables
    ProductCategory.CANDY -> R.string.candy
}