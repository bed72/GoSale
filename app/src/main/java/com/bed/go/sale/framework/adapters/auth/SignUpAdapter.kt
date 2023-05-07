package com.bed.go.sale.framework.adapters.auth

import javax.inject.Inject

import io.ktor.http.HttpMethod
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.client.request.setBody

import com.bed.go.core.domain.paths.Paths

import com.bed.go.sale.framework.adapters.http.call

import com.bed.go.sale.data.alias.SignUpModelType

import com.bed.go.sale.data.models.request.auth.SignUpRequestModel
import com.bed.go.sale.data.models.request.auth.SignUpDataRequestModel

interface SignUpAdapter {
    suspend operator fun invoke(name: String, email: String, password: String): SignUpModelType
}

class SignUpAdapterImpl @Inject constructor(private val client: HttpClient) : SignUpAdapter {
    override suspend fun invoke(name: String, email: String, password: String): SignUpModelType =
        client.call {
            method = HttpMethod.Post
            url(Paths.SIGN_UP_SELLER.value)
            setBody(buildBody(name, email, password))
            contentType(ContentType.Application.Json)
        }

    private fun buildBody(name: String, email: String, password: String) =
        SignUpRequestModel(email = email, password = password, data = SignUpDataRequestModel(name))
}