package com.sleepingcats.komposer.presentation.news

import com.sleepingcats.komposer.domain.news.usecase.GetNewsListUseCase
import com.sleepingcats.komposer.presentation.base.viewModel.AppViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NewsViewModel(
    private val getNewsListUseCase: GetNewsListUseCase
) : AppViewModel() {

    private var _state = MutableStateFlow(NewsState(isLoading = true))
    val state: StateFlow<NewsState> get() = _state

    fun getNews() {
        _state.value = NewsState(isLoading = true)
        launcher.launch(useCase = getNewsListUseCase, onError = {
            _state.value = NewsState(error = it.message)
        }) {
            _state.value = NewsState(isLoading = false, news = it)
        }
    }
}