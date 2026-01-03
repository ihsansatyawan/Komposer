package com.sleepingcats.komposer.domain.base.usecase

abstract class BaseUseCaseAsync<Args, Result> : BaseUseCase() {
    protected abstract suspend fun execute(args: Args): Result
    suspend operator fun invoke(args: Args): Result {
        return invokeUseCase(args)
    }

    private suspend fun invokeUseCase(args: Args): Result {
        return execute(args = args)
    }
}