package com.easyhooon.metroapplication.feature.search

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
class SearchPresenter(
    @Assisted private val navigator: Navigator,
    private val bookRepository: BookRepository,
) : Presenter<SearchUiState> {

    @CircuitInject(SearchScreen::class, AppScope::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): SearchPresenter
    }

    @Composable
    override fun present(): SearchUiState {
        return SearchUiState()
    }
}
