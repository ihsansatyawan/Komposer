package com.sleepingcats.komposer.presentation.base.viewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel {
    actual val scope: CoroutineScope
        get() = CoroutineScope(Dispatchers.IO)

    fun clear() {
        scope.cancel()
    }
}