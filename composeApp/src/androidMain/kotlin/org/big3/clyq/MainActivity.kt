package org.big3.clyq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.big3.clyq.navigation.MainDestinations
import org.big3.clyq.navigation.MainNavGraph
import org.big3.clyq.navigation.rememberClyqNavController
import org.big3.clyq.ui.views.loginView.LoginScreen
import org.big3.clyq.ui.views.splashView.SplashScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClyqApp()
        }
    }
}

@Composable
fun ClyqApp(){
    MaterialTheme {
        val mNavController = rememberClyqNavController()

        NavHost(
            navController = mNavController.navController,
            startDestination = MainDestinations.Splash.route
        ){
            composable(route = MainDestinations.Splash.route) {
                SplashScreen(
                    navController = mNavController.navController
                )
            }

            composable(route = MainDestinations.Main.route) {
                MainNavGraph() {
                    //Force logout
                    mNavController.navController.navigate(MainDestinations.Auth.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }

            navigation(startDestination = "login", route = MainDestinations.Auth.route) {
                composable("login") {
                    LoginScreen(
                        onNavigateToMain = {
                            mNavController.navController.navigate(MainDestinations.Main.route) {
                                popUpTo(0) { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                    )
                }
            }
        }
    }
}

