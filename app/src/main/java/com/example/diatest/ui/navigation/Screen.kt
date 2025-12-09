package com.example.diatest.ui.navigation

import kotlinx.serialization.Serializable

internal sealed class Screen() {

    val route: String
        get() = this::class.qualifiedName ?: error("Unknown route")

    @Serializable
    object ProductScreen : Screen()

    @Serializable
    object HistoryScreen : Screen()

    @Serializable
    object CartScreen : Screen()

    @Serializable
    object ProfileScreen : Screen()

    @Serializable
    object AddProductScreen : Screen()


}