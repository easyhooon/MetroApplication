package com.easyhooon.metroapplication.feature.detail

import androidx.compose.runtime.Composable
import com.easyhooon.metroapplication.core.data.api.repository.BookRepository
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class DetailPresenter(
    @Assisted private val screen: DetailScreen,
    @Assisted private val navigator: Navigator,
    private val bookRepository: BookRepository,
) : Presenter<DetailUiState> {

    @CircuitInject(DetailScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(screen: DetailScreen, navigator: Navigator): DetailPresenter
    }

    @Composable
    override fun present(): DetailUiState {
        return DetailUiState(id = screen.id)
    }
}
