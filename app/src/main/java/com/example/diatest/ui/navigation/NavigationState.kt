package com.example.diatest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

internal class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: Screen) {
        navHostController.navigate(route = route) {
            launchSingleTop = true
            restoreState = true
            popUpTo(
                route = navHostController.graph.safeStartDestinationRoute,
                popUpToBuilder = {
                    saveState = true
                })
        }
    }

    fun back() {
        navHostController.popBackStack()
    }

    @Composable
    fun currentScreen(): Screen? {
        val backStackEntry by navHostController.currentBackStackEntryAsState()
        return when (backStackEntry?.destination?.route) {
            Screen.ProductScreen.route -> Screen.ProductScreen
            Screen.HistoryScreen.route -> Screen.HistoryScreen
            Screen.CartScreen.route -> Screen.CartScreen
            Screen.ProfileScreen.route -> Screen.ProfileScreen
            Screen.AddProductScreen.route -> Screen.AddProductScreen
            else -> null
        }
    }

}

@Composable
internal fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState = remember {
    NavigationState(navHostController)
}

val NavGraph.safeStartDestinationRoute: String
    get() = startDestinationRoute ?: error("Start destination route is null")