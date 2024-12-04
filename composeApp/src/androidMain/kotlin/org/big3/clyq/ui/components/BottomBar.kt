package org.big3.clyq.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import org.big3.clyq.navigation.BottomNavItem
import org.big3.clyq.navigation.ClyqNavController

@Composable
fun BottomNavigationBar(
    navController: ClyqNavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Groups,
        BottomNavItem.Search,
        BottomNavItem.Profile
    )
    val navBackStackEntry by navController.navController.currentBackStackEntryAsState()
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    BottomAppBar(modifier = Modifier.height(56.dp + bottomPadding)) {
        items.forEach { item ->
            NavigationBarItem(
                selected = navBackStackEntry?.destination?.hierarchy?.any {
                    it.route == item.route
                } == true,
                onClick = {
                    if (navBackStackEntry?.destination?.hierarchy?.any {
                            it.route == item.route
                        } == false) {
                        navController.navigateToBottomBarRoute(item.route)
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                })
        }
    }
}

