package org.big3.clyq.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberClyqNavController(
    navController: NavHostController = rememberNavController()
): ClyqNavController = remember(navController) {
    ClyqNavController(navController)
}


/**
 * Responsible for holding UI Navigation logic
 */
@Stable
class ClyqNavController(
    val navController: NavHostController
) {
    private val currentRoute: String?
        get() = navController.currentDestination?.route


    fun backPressed() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToGroupDetails(groupId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            val route = "${MainDestinations.GroupDetail.route}/$groupId"
            navController.navigate(route)
        } else {
            Log.d("ClyqNavController", "navigateToGroupDetails lifecycle is not resumed")
        }
    }

    fun navigateToCreateGroup(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            val route = MainDestinations.NewGroup.route
            navController.navigate(route)
        } else {
            Log.d("ClyqNavController", "navigateToCreateGroup lifecycle is not resumed")
        }
    }


    fun navigateToDisplayMembers(
        memberType: String,
        participants: String,
        from: NavBackStackEntry
    ) {
        if (from.lifecycleIsResumed()) {
            val route =
                MainDestinations.MemberDetails.createRoute(memberType, participants)
            navController.navigate(route)
        } else {
            Log.d("ClyqNavController", "navigateToDisplayMembers lifecycle is not resumed")
        }
    }
}


/**
 * de-duplicate navigation events
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}