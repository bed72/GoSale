package com.bed.go.sale.data.datasources.auth

import javax.inject.Inject

import com.bed.go.core.domain.alias.SignUpType

import com.bed.go.core.domain.entities.Response

import com.bed.go.sale.framework.adapters.auth.SignUpAdapter

import com.bed.go.sale.data.models.response.auth.toMapper
import com.bed.go.sale.data.models.response.message.toMapper
import com.bed.go.core.data.datasources.auth.SignUpDataSource

class SignUpDataSourceImpl @Inject constructor(
    private val adapter: SignUpAdapter
) : SignUpDataSource {
    override suspend fun invoke(name: String, email: String, password: String): SignUpType =
        adapter(name, email, password)
            .map { (status, data) -> Response(status, data.toMapper()) }
            .mapLeft { (status, data) -> Response(status, data.toMapper()) }
}
