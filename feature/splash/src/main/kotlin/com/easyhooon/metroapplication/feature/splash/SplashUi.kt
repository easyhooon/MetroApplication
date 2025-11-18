package com.easyhooon.metroapplication.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhooon.metroapplication.feature.screens.SplashScreen
import com.easyhooon.metroapplication.feature.screens.SplashUiState
import com.slack.circuit.codegen.annotations.CircuitInject
import dev.zacsweers.metro.AppScope

@CircuitInject(SplashScreen::class, AppScope::class)
@Composable
fun SplashUi(state: SplashUiState, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Splash Screen")
    }
}