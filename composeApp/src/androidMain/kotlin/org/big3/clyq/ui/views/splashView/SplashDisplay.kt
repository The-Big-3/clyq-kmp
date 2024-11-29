package org.big3.clyq.ui.views.splashView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import org.big3.clyq.ui.components.LoadingSpinner


@Composable
fun SplashScreen(
    navController: NavController,
//    authViewModel: AuthViewModel = hiltViewModel()
) {

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