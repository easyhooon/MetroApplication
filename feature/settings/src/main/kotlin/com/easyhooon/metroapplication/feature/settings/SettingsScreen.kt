package com.easyhooon.metroapplication.feature.settings

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object SettingsScreen : Screen

data class SettingsUiState(
    val version: String = "1.0.0",
) : CircuitUiState
