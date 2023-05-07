package com.bed.go.sale.data.repositories.auth

import javax.inject.Inject

import com.bed.go.core.domain.alias.SignUpType

import com.bed.go.core.data.datasources.auth.SignUpDataSource
import com.bed.go.core.data.repositories.auth.SignUpRepository

class SignUpRepositoryImpl @Inject constructor(
    private val dataSource: SignUpDataSource
) : SignUpRepository {
    override suspend fun invoke(name: String, email: String, password: String): SignUpType =
        dataSource(name, email, password)
}