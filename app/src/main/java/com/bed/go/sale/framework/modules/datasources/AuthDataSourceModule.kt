package com.bed.go.sale.framework.modules.datasources

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.go.core.data.datasources.auth.SignUpDataSource
import com.bed.go.sale.data.datasources.auth.SignUpDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthDataSourceModule {

    @Binds
    fun bindSignUpDataSource(dataSource: SignUpDataSourceImpl): SignUpDataSource

}
