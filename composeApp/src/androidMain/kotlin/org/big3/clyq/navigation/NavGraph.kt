package org.big3.clyq.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.navigation
import org.big3.clyq.ui.components.BottomNavigationBar
import org.big3.clyq.ui.views.eventView.EventCreateScreen
import org.big3.clyq.ui.views.eventView.EventDetailsScreen
import org.big3.clyq.ui.views.eventView.EventEditScreen
import org.big3.clyq.ui.views.groupView.GroupCreateScreen
import org.big3.clyq.ui.views.groupView.GroupDetailScreen
import org.big3.clyq.ui.views.groupView.GroupEditScreen
import org.big3.clyq.ui.views.groupView.MyGroupScreen
import org.big3.clyq.ui.views.homeView.HomeScreen
import org.big3.clyq.ui.views.memberView.MembersDetailsScreen
import org.big3.clyq.ui.views.profileView.ProfileScreen
import org.big3.clyq.ui.views.searchView.SearchScreen

@Composable
fun MainNavGraph(
    onLogoutPressed: () -> Unit
) {
    val mNavController = rememberClyqNavController()
    val currentBackStackEntry by mNavController.navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute != null && (currentRoute.startsWith(MainDestinations.GroupDetail.route) || currentRoute.startsWith(
                    MainDestinations.NewGroup.route
                ) || currentRoute.startsWith(MainDestinations.MemberDetails.route) || currentRoute.startsWith(
                    GroupSection.EditGroup.route
                ) || currentRoute.startsWith(GroupSection.EditEvent.route) || currentRoute.startsWith(
                    GroupSection.CreateEvent.route
                ))
            ) {
                // Hide bottom bar for detailed routes
            } else {
                BottomNavigationBar(mNavController)
            }
        }
    ) { paddingValues ->
        //Scaffold padding values of Material3 different from Material1
        val adjustedPaddingValues = PaddingValues(
            start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
            top = 0.dp,
            end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
            bottom = paddingValues.calculateBottomPadding()
        )

        NavHost(
            navController = mNavController.navController,
            startDestination = MainDestinations.Main.route
        ) {

            addBottomGraph(
                adjustedPaddingValues,
                mNavController,
                onLogoutPressed = {
                    onLogoutPressed()
                })

            composable(
                route = "${MainDestinations.GroupDetail.route}/{groupId}",
                arguments = listOf(navArgument("groupId") { type = NavType.StringType }),
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val groupId = arguments.getString("groupId") ?: ""
                GroupDetailScreen (
                    groupId = groupId,
                    openMembersView = { memberType, participants ->
                        mNavController.navigateToDisplayMembers(
                            memberType,
                            participants,
                            backStackEntry
                        )
                    },
                    openEditMode = {
                        mNavController.navController.navigate(
                            GroupSection.EditGroup.createRoute(
                                groupId
                            )
                        )
                    },
                    goBackPress = mNavController::backPressed
                )
            }

            composable(
                route = MainDestinations.NewGroup.route
            ) {
                GroupCreateScreen(onBackPressed = mNavController::backPressed)
            }

            composable(
                route = MainDestinations.MemberDetails.route,
                arguments = listOf(
                    navArgument("displayMemberType") { type = NavType.StringType },
                    navArgument("participants") { type = NavType.StringType },
                )
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val displayGroupMembers = arguments.getString("displayMemberType") ?: ""
                val participants = arguments.getString("participants")?:""
                MembersDetailsScreen(
                    displayMemberType = displayGroupMembers,
                    participants = participants,
                    onBackPressed = mNavController::backPressed
                )
            }
        }
    }


}


private fun NavGraphBuilder.addBottomGraph(
    paddingValues: PaddingValues,
    clyqNavController: ClyqNavController,
    onLogoutPressed: () -> Unit,
) {
    navigation(startDestination = BottomNavItem.Home.route, route = MainDestinations.Main.route) {
        addHomeGraph(paddingValues, clyqNavController, openGroupDetail = { groupId, from ->
            clyqNavController.navigateToGroupDetails(groupId = groupId, from = from)
        },
            openMemberDetails = { memberType, participants,from ->
                clyqNavController.navigateToDisplayMembers(memberType, participants, from)
            },
            onForceLogout = { onLogoutPressed() }
        )
        addGroupsGraph(
            paddingValues,
            clyqNavController,
            openGroupDetail = { groupId, from ->
                clyqNavController.navigateToGroupDetails(
                    groupId = groupId,
                    from = from
                )
            },
            createNewGroup = { from ->
                clyqNavController.navigateToCreateGroup(from)
            },
            openMemberDetails = { memberType, participants, from ->
                clyqNavController.navigateToDisplayMembers(memberType, participants, from)
            }
        )
        addSearchGraph(paddingValues, clyqNavController, openGroupDetail = { groupId, from ->
            clyqNavController.navigateToGroupDetails(groupId = groupId, from = from)
        })
        addProfileGraph(paddingValues, onLogoutPressed = {
            onLogoutPressed()

        })
    }

}


private fun NavGraphBuilder.addHomeGraph(
    paddingValues: PaddingValues,
    clyqNavController: ClyqNavController,
    openGroupDetail: (String, NavBackStackEntry) -> Unit,
    openMemberDetails: (memberType: String, participants: String, from: NavBackStackEntry) -> Unit,
    onForceLogout: () -> Unit
) {
    navigation(startDestination = HomeSection.HomeMain.route, route = BottomNavItem.Home.route) {
        composable(HomeSection.HomeMain.route) { from ->
            HomeScreen(
                paddingValues = paddingValues,
                onNavigateToRoute = { route ->
                    clyqNavController.navController.navigate(route)
                },
                openGroupList = { id ->
                    openGroupDetail(id, from)
                },
                onForceLogout = {
                    onForceLogout()
                }
            )
        }
        composable(

            route = HomeSection.EventDetails.route,
            arguments = listOf(
                navArgument("eventId") { type = NavType.StringType },
                navArgument("userInfo") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId") ?: "-1"
            val userInfo = backStackEntry.arguments?.getString("userInfo") ?: "-1"
            EventDetailsScreen(
                paddingValues = paddingValues,
                eventId = eventId,
                userEmail =userInfo,
                onBackPressed = {
                    clyqNavController.backPressed()
                },
                openMembersView = { memberType, participants ->
                    openMemberDetails(memberType, participants, backStackEntry)

                }
            )
        }


    }
}

private fun NavGraphBuilder.addGroupsGraph(
    paddingValues: PaddingValues,
    clyqNavController: ClyqNavController,
    openGroupDetail: (String, NavBackStackEntry) -> Unit,
    createNewGroup: (NavBackStackEntry) -> Unit,
    openMemberDetails: (memberType: String, participants: String, from: NavBackStackEntry) -> Unit,
) {
    navigation(
        startDestination = GroupSection.GroupMain.route,
        route = BottomNavItem.Groups.route
    ) {
        composable(GroupSection.GroupMain.route) { from ->
            MyGroupScreen(paddingValues,
                onNavigateToRoute = {
                    clyqNavController.navController.navigate(it)
                },
                openGroupDetail = { id ->
                    openGroupDetail(id, from)
                },
                openCreateGroup = {
                    createNewGroup(from)
                },
                openGroupAsOwner = { groupId ->

                    openGroupDetail(groupId, from)
                })
        }

        composable(
            route = GroupSection.EditGroup.route,
            arguments = listOf(navArgument("groupId") { type = NavType.StringType })
        ) { backStackEntry ->

            val groupId = backStackEntry.arguments?.getString("groupId") ?: "-1"
            GroupEditScreen(
                paddingValues = paddingValues,
                groupId = groupId,
                onBackPressed = {
                    clyqNavController.backPressed()
                },
                openMembersView = { memberType,participants ->
                    openMemberDetails(memberType, participants, backStackEntry)
                },
                onAddEvent = {
                    clyqNavController.navController.navigate(
                        GroupSection.CreateEvent.createRoute(
                            groupId
                        )
                    )
                },
                onEditEvent = { eventId ->
                    clyqNavController.navController.navigate(
                        GroupSection.EditEvent.createRoute(
                            eventId
                        )
                    )
                }
            )

        }

        composable(
            route = GroupSection.CreateEvent.route,
            arguments = listOf(navArgument("groupId") { type = NavType.StringType })
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId") ?: "-1"
            EventCreateScreen(
                paddingValues = paddingValues,
                groupId = groupId,
                onBackPressed = {
                    clyqNavController.backPressed()
                }
            )
        }

        composable(
            route = GroupSection.EditEvent.route,
            arguments = listOf(navArgument("eventId") { type = NavType.StringType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId") ?: "-1"
            EventEditScreen(
                paddingValues = paddingValues,
                eventId = eventId,
                openMembersView = { memberType, participants ->
                    openMemberDetails(memberType, participants, backStackEntry)
                },
                onBackPressed = {
                    clyqNavController.backPressed()
                })
        }

    }
}

private fun NavGraphBuilder.addSearchGraph(
    paddingValues: PaddingValues,
    clyqNavController: ClyqNavController,
    openGroupDetail: (String, NavBackStackEntry) -> Unit
) {
    navigation(startDestination = "search_main", route = BottomNavItem.Search.route) {
        composable("search_main") { from ->
            SearchScreen(
                paddingValues,
                onNavigateToRoute = {

                },
                openGroupDetail = { id ->
                    openGroupDetail(id, from)
                }

            )
        }
    }

}

private fun NavGraphBuilder.addProfileGraph(
    paddingValues: PaddingValues,
    onLogoutPressed: () -> Unit
) {
    navigation(startDestination = "profile_main", route = BottomNavItem.Profile.route) {
        composable("profile_main") {
            ProfileScreen(paddingValues = paddingValues, onLogoutPressed = { onLogoutPressed() })
        }
    }
}
