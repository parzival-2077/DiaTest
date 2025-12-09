package com.example.diatest.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.diatest.ui.navigation.NavigationItem
import com.example.diatest.ui.navigation.NavigationState

@Composable
internal fun NavigationBar(
    items: List<NavigationItem>,
    navigationState: NavigationState
) {
    NavigationBar {
        val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
        items.forEach { item ->
            NavigationBarItem(
                selected = navBackStackEntry?.destination?.route == item.screen.route,
                onClick = {
                    navigationState.navigateTo(item.screen)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(item.labelResId))
                }
            )
        }
    }
}