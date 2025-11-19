package com.easyhooon.metroapplication.feature.login

import androidx.compose.runtime.Composable
import com.easyhooon.metroapplication.core.data.api.repository.AuthRepository
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class LoginPresenter(
    @Assisted private val navigator: Navigator,
    private val authRepository: AuthRepository,
) : Presenter<LoginUiState> {

    @CircuitInject(LoginScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): LoginPresenter
    }

    @Composable
    override fun present(): LoginUiState {
        return LoginUiState()
    }
}
