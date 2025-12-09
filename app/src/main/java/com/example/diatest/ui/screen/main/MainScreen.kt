package com.example.diatest.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.diatest.R
import com.example.diatest.di.ViewModelFactory
import com.example.diatest.ui.component.NavigationBar
import com.example.diatest.ui.navigation.AppNavGraph
import com.example.diatest.ui.navigation.NavigationItem
import com.example.diatest.ui.navigation.Screen
import com.example.diatest.ui.navigation.rememberNavigationState
import com.example.diatest.ui.screen.add_product.AddProductScreen
import com.example.diatest.ui.screen.cart.CartScreen
import com.example.diatest.ui.screen.history.HistoryScreen
import com.example.diatest.ui.screen.products.ProductsScreen
import com.example.diatest.ui.screen.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModelFactory: ViewModelFactory) {
    val navigationState = rememberNavigationState()

    val navigationItems = listOf(
        NavigationItem.ProductScreen,
        NavigationItem.HistoryScreen,
        NavigationItem.CartScreen,
        NavigationItem.ProfileScreen
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val text = when (navigationState.currentScreen()) {
                        Screen.CartScreen -> stringResource(R.string.cart)
                        Screen.HistoryScreen -> stringResource(R.string.history)
                        Screen.ProductScreen -> stringResource(R.string.products)
                        Screen.ProfileScreen -> stringResource(R.string.profile)
                        Screen.AddProductScreen -> stringResource(R.string.add_product)
                        else -> ""
                    }
                    Text(text = text, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    if (navigationState.currentScreen() == Screen.AddProductScreen) {
                        IconButton(
                            onClick = { navigationState.back() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                },
                actions = {
                    if (navigationState.currentScreen() == Screen.ProductScreen) {
                        IconButton(
                            onClick = {
                                navigationState.navigateTo(Screen.AddProductScreen)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(R.string.add_product)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                items = navigationItems,
                navigationState = navigationState
            )
        }
    ) { paddingValues ->
        AppContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navHostController = navigationState.navHostController,
            viewModelFactory = viewModelFactory
        )
    }
}

@Composable
private fun AppContent(
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory,
    modifier: Modifier = Modifier
) {
    AppNavGraph(
        modifier = modifier,
        navHostController = navHostController,
        productScreen = { ProductsScreen(viewModel = viewModel(factory = viewModelFactory)) },
        historyScreen = { HistoryScreen(viewmodel = viewModel(factory = viewModelFactory)) },
        profileScreen = { ProfileScreen(viewModel = viewModel(factory = viewModelFactory)) },
        cartScreen = { CartScreen(viewModel(factory = viewModelFactory)) },
        addProductScreen = { onSuccess ->
            AddProductScreen(
                viewModel = viewModel(factory = viewModelFactory),
                onSuccess = onSuccess
            )
        }
    )
}