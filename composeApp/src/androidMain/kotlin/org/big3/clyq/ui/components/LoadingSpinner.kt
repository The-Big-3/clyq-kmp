package org.big3.clyq.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingSpinner(
    alphaValue:Float = 0.1f
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(.1f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}