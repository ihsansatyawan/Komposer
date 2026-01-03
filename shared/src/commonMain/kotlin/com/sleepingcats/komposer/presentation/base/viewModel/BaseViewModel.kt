package com.sleepingcats.komposer.presentation.base.viewModel

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel {
    val scope: CoroutineScope
}