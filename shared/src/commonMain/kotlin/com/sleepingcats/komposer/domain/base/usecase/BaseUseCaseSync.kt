package com.sleepingcats.komposer.domain.base.usecase

abstract class BaseUseCaseSync<Args, Result> : BaseUseCase() {
    protected abstract fun execute(args: Args): Result
    operator fun invoke(args: Args): Result {
        return execute(args)
    }
}