package com.easyhooon.metroapplication.feature.home

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object HomeScreen : Screen

data class HomeUiState(
    val title: String = "Home",
) : CircuitUiState
