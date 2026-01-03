package com.sleepingcats.komposer.presentation.base.viewModel

import com.sleepingcats.komposer.presentation.base.UseCaseLauncher
import kotlinx.coroutines.CoroutineScope

abstract class AppViewModel : BaseViewModel() {
    protected fun createLauncher(scope: CoroutineScope) =
        object : UseCaseLauncher {
            override val scope: CoroutineScope
                get() = scope
        }

    protected val launcher by lazy { createLauncher(scope = scope) }
}