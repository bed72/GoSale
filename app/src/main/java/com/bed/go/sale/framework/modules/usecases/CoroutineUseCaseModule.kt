package com.bed.go.sale.framework.modules.usecases

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.go.core.data.usecases.coroutines.CoroutinesUseCase
import com.bed.go.core.data.usecases.coroutines.CoroutinesUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineUseCaseModule {
    @Binds
    fun bindCoroutineUseCase(value: CoroutinesUseCaseImpl): CoroutinesUseCase
}
