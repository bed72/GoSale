package com.bed.go.sale.framework.adapters.authentication

import javax.inject.Inject

import io.ktor.http.HttpMethod
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.client.request.setBody

import com.bed.go.core.domain.constants.Paths
import com.bed.go.core.domain.params.authentication.SignUpParams

import com.bed.go.sale.framework.adapters.http.call

import com.bed.go.sale.data.alias.SignUpModelType
import com.bed.go.sale.data.models.request.authentication.SignUpRequestModel

interface SignUpAdapter {
    suspend operator fun invoke(params: SignUpParams): SignUpModelType
}

class SignUpAdapterImpl @Inject constructor(private val client: HttpClient) : SignUpAdapter {
    override suspend fun invoke(params: SignUpParams): SignUpModelType =
        client.call {
            setBody(mapper(params))
            method = HttpMethod.Post
            url(Paths.SIGN_UP_SELLER.value)
            contentType(ContentType.Application.Json)
        }

    private fun mapper(params: SignUpParams) =
        SignUpRequestModel(params.name, params.email, params.password)
}
