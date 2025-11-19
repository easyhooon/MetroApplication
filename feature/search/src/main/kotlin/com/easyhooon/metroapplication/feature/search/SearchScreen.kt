package com.easyhooon.metroapplication.feature.search

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object SearchScreen : Screen

data class SearchUiState(
    val query: String = "",
) : CircuitUiState
