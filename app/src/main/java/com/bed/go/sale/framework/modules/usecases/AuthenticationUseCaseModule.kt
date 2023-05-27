package com.bed.go.sale.framework.modules.usecases

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.go.core.data.usecases.authentication.SignUpUseCase
import com.bed.go.core.data.usecases.authentication.SignUpUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticationUseCaseModule {
    @Binds
    fun bindSignUpUseCase(dataSource: SignUpUseCaseImpl): SignUpUseCase
}
