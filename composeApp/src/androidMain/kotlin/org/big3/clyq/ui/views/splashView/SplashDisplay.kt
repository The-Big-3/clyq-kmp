package org.big3.clyq.ui.views.splashView

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.big3.clyq.constants.LoginState
import org.big3.clyq.navigation.MainDestinations
import org.big3.clyq.ui.components.LoadingSpinner
import org.big3.clyq.ui.viewModels.AuthViewModel


@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {

    LaunchedEffect(Unit){
        delay(3000)
        authViewModel.loginState.collect{ loginState ->
            Log.e("MainActivity","Check log in status -- $loginState")
            when(loginState){
                LoginState.Idle ->{
                    navController.navigate(MainDestinations.Auth.route) {
                        popUpTo(MainDestinations.Splash.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
                is LoginState.LoggedIn -> {
                    navController.navigate(MainDestinations.Main.route) {
                        popUpTo(MainDestinations.Splash.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }

                else ->{

                }
            }


        }
    }


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(listOf(Color(0xff7057f5), Color(0xff86f7fa)))
                ),
            contentAlignment = Alignment.Center
        ) {
            LoadingSpinner()
        }

    }
}