package com.easyhooon.metroapplication.feature.login

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object LoginScreen : Screen

data class LoginUiState(
    val email: String = "",
    val password: String = "",
) : CircuitUiState
