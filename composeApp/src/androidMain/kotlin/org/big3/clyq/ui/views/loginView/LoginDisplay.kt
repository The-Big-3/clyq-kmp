package org.big3.clyq.ui.views.loginView

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.big3.clyq.constants.LoginState
import org.big3.clyq.ui.viewModels.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = viewModel(),
    onNavigateToMain: () -> Unit,
) {
    val loginState by authViewModel.loginState.collectAsState()
    val requestLoading by authViewModel.requestLoading.collectAsState()
    val mContext = LocalContext.current

    LaunchedEffect(loginState) {
        Log.e("LoginState", " ---- ${loginState.toString()}")
        when (loginState) {
            is LoginState.LoggedIn -> {
                onNavigateToMain()
            }

            is LoginState.Error -> {}
            else -> {}
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(listOf(Color(0xff7057f5), Color(0xff86f7fa)))
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        authViewModel.login(mContext)
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff001787),
                        contentColor = Color.White,
                        disabledContainerColor = Color.LightGray.copy(alpha = .3f),
                        disabledContentColor = Color.DarkGray
                    )
                ) {
                    Text("Login")
                }
            }
        }
    }


}