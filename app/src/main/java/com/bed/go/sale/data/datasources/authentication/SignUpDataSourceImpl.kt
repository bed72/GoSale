package com.bed.go.sale.data.datasources.authentication

import javax.inject.Inject

import com.bed.go.core.domain.alias.SignUpType
import com.bed.go.core.domain.params.authentication.SignUpParams

import com.bed.go.sale.framework.adapters.authentication.SignUpAdapter

import com.bed.go.sale.data.models.response.authentication.toMapper
import com.bed.go.sale.data.models.response.message.toMapper
import com.bed.go.core.data.datasources.authentication.SignUpDataSource

class SignUpDataSourceImpl @Inject constructor(
    private val adapter: SignUpAdapter
) : SignUpDataSource {
    override suspend fun invoke(params: SignUpParams): SignUpType =
        adapter(params)
            .map { (_, data) -> data.toMapper() }
            .mapLeft { (_, data) -> data.toMapper() }

}
