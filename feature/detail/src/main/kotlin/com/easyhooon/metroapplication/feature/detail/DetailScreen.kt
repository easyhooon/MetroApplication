package com.easyhooon.metroapplication.feature.detail

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailScreen(val id: String) : Screen

data class DetailUiState(
    val id: String,
    val title: String = "Detail",
) : CircuitUiState
