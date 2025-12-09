package com.example.diatest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
internal fun AppNavGraph(
    navHostController: NavHostController,
    productScreen: @Composable () -> Unit,
    historyScreen: @Composable () -> Unit,
    profileScreen: @Composable () -> Unit,
    cartScreen: @Composable () -> Unit,
    addProductScreen: @Composable ((onSuccess: () -> Unit) -> Unit),
    modifier: Modifier = Modifier,
    startDestination: Screen = Screen.ProductScreen
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<Screen.ProductScreen> { productScreen() }
        composable<Screen.HistoryScreen> { historyScreen() }
        composable<Screen.CartScreen> { cartScreen() }
        composable<Screen.ProfileScreen> { profileScreen() }
        composable<Screen.AddProductScreen> {
            addProductScreen { navHostController.popBackStack() }
        }
    }
}