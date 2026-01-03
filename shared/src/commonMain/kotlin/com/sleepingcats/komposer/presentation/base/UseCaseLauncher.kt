package com.sleepingcats.komposer.presentation.base

import com.sleepingcats.komposer.domain.base.model.NoArgs
import com.sleepingcats.komposer.domain.base.model.NoResult
import com.sleepingcats.komposer.domain.base.usecase.BaseUseCaseAsync
import com.sleepingcats.komposer.domain.base.usecase.BaseUseCaseSync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface UseCaseLauncher {

    val scope: CoroutineScope

    fun <T, Args, Result> launch(
        useCase: T,
        args: Args,
        onError: ((Exception) -> Unit)? = null,
        resultCallback: (Result) -> Unit,
    ): Job where T : BaseUseCaseAsync<Args, Result> {
        return scope.launch {
            try {
                val result = useCase(args)
                resultCallback(result)
            } catch (e: Exception) {
                onError?.invoke(e)
            }
        }
    }

    fun <T, Result> launch(
        useCase: T,
        onError: ((Exception) -> Unit)? = null,
        resultCallback: (Result) -> Unit,
    ): Job where T : BaseUseCaseAsync<NoArgs, Result> {
        return scope.launch {
            try {
                val result = useCase(NoArgs())
                resultCallback(result)
            } catch (e: Exception) {
                onError?.invoke(e)
            }
        }
    }

    fun <T> launch(
        useCase: T,
        onError: ((Exception) -> Unit)? = null,
        resultCallback: () -> Unit,
    ): Job where T : BaseUseCaseAsync<NoArgs, NoResult> {
        return scope.launch {
            try {
                useCase(NoArgs())
                resultCallback()
            } catch (e: Exception) {
                onError?.invoke(e)
            }
        }
    }

    fun <T, Args> launch(
        useCase: T,
        args: Args,
        onError: ((Exception) -> Unit)? = null,
        resultCallback: () -> Unit,
    ): Job where T : BaseUseCaseAsync<Args, NoResult> {
        return scope.launch {
            try {
                useCase(args)
                resultCallback()
            } catch (e: Exception) {
                onError?.invoke(e)
            }
        }
    }

    fun <T, Result> launch(
        useCase: T,
        onError: ((Exception) -> Unit)? = null,
        resultCallback: (Result) -> Unit,
    ) where T : BaseUseCaseSync<NoArgs, Result> {
        try {
            resultCallback(useCase(NoArgs()))
        } catch (e: Exception) {
            onError?.invoke(e)
        }
    }
}