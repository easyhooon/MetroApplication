package com.easyhooon.metroapplication.feature.splash

import androidx.compose.runtime.Composable
import com.easyhooon.metroapplication.feature.screens.SplashScreen
import com.easyhooon.metroapplication.feature.screens.SplashUiState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class SplashPresenter(
    @Assisted private val navigator: Navigator,
) : Presenter<SplashUiState> {

    @CircuitInject(SplashScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): SplashPresenter
    }

    @Composable
    override fun present(): SplashUiState {
        return SplashUiState
    }
}