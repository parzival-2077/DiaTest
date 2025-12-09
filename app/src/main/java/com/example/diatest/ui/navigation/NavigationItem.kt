package com.example.diatest.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.diatest.R

internal sealed class NavigationItem(
    val screen: Screen,
    val labelResId: Int,
    val icon: ImageVector
) {

    data object ProductScreen : NavigationItem(
        Screen.ProductScreen,
        labelResId = R.string.products,
        icon = Icons.AutoMirrored.Filled.List
    )

    data object HistoryScreen : NavigationItem(
        Screen.HistoryScreen,
        labelResId = R.string.history,
        icon = Icons.Default.Star
    )

    data object CartScreen : NavigationItem(
        Screen.CartScreen,
        labelResId = R.string.cart,
        icon = Icons.Default.ShoppingCart
    )

    data object ProfileScreen : NavigationItem(
        Screen.ProfileScreen,
        labelResId = R.string.profile,
        icon = Icons.Default.Person
    )
}