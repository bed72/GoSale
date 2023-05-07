package com.bed.go.sale.framework.modules.adapters

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.go.sale.framework.adapters.auth.SignUpAdapter
import com.bed.go.sale.framework.adapters.auth.SignUpAdapterImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthAdapterModule {

    @Binds
    fun bindSignUpAdapter(adapter: SignUpAdapterImpl): SignUpAdapter

}
