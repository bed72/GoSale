package com.bed.go.core.data.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCase<in P, out R> {
    operator fun invoke(params: P): Flow<R> = flow {
        emit(doWork(params))
    }

    protected abstract suspend fun doWork(params: P): R
}

abstract class FlowUseCase<in P, out R : Any> {

    suspend operator fun invoke(params: P): Flow<R> = createFlowObservable(params)

    protected abstract suspend fun createFlowObservable(params: P): Flow<R>
}