package com.bed.go.sale.data.repositories.authentication

import javax.inject.Inject

import com.bed.go.core.domain.alias.SignUpType

import com.bed.go.core.data.datasources.authentication.SignUpDataSource
import com.bed.go.core.data.repositories.authentication.SignUpRepository
import com.bed.go.core.domain.params.authentication.SignUpParams

class SignUpRepositoryImpl @Inject constructor(
    private val dataSource: SignUpDataSource
) : SignUpRepository {
    override suspend fun invoke(params: SignUpParams): SignUpType = dataSource(params)
}
