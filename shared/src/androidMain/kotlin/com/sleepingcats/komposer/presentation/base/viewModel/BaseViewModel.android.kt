package com.sleepingcats.komposer.presentation.base.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual abstract class BaseViewModel: ViewModel() {
    actual val scope: CoroutineScope
        get() = viewModelScope
}