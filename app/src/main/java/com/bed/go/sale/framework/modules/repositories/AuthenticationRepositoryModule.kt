package com.bed.go.sale.framework.modules.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.go.core.data.repositories.authentication.SignUpRepository
import com.bed.go.sale.data.repositories.authentication.SignUpRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticationRepositoryModule {
    @Binds
    fun bindSignUpRepository(dataSource: SignUpRepositoryImpl): SignUpRepository
}
