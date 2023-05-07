package com.bed.go.sale.framework.modules.usecases

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.go.core.data.usecases.auth.SignUpUseCase
import com.bed.go.core.data.usecases.auth.SignUpUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthUseCaseModule {

    @Binds
    fun bindSignUpUseCase(dataSource: SignUpUseCaseImpl): SignUpUseCase

}
