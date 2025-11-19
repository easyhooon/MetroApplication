package com.easyhooon.metroapplication.feature.settings

import androidx.compose.runtime.Composable
import com.easyhooon.metroapplication.core.data.api.repository.UserRepository
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class SettingsPresenter(
    @Assisted private val navigator: Navigator,
    private val userRepository: UserRepository,
) : Presenter<SettingsUiState> {

    @CircuitInject(SettingsScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): SettingsPresenter
    }

    @Composable
    override fun present(): SettingsUiState {
        return SettingsUiState()
    }
}
