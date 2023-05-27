package com.bed.go.sale.framework.modules.adapters

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.go.sale.framework.adapters.authentication.SignUpAdapter
import com.bed.go.sale.framework.adapters.authentication.SignUpAdapterImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticationAdapterModule {

    @Binds
    fun bindSignUpAdapter(adapter: SignUpAdapterImpl): SignUpAdapter

}
