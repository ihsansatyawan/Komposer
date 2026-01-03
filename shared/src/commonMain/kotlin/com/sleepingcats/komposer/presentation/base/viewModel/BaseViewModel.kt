package com.sleepingcats.komposer.presentation.base.viewModel

import kotlinx.coroutines.CoroutineScope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect abstract class BaseViewModel() {
    val scope: CoroutineScope
}