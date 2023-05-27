package com.bed.go.sale.framework.adapters.http

import arrow.core.left
import arrow.core.right
import arrow.core.Either

import io.ktor.client.call.body
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.request.HttpRequestBuilder

import com.bed.go.sale.data.models.response.ResponseModel

suspend inline fun <reified F, reified S> HttpClient.call(
    block: HttpRequestBuilder.() -> Unit,
): Either<ResponseModel<F>, ResponseModel<S>> {
    val response = request { block() }

    return when (response.status) {
        HttpStatusCode.BadRequest -> failure(response)
        HttpStatusCode.Unauthorized -> failure(response)
        HttpStatusCode.TooManyRequests -> failure(response)
        HttpStatusCode.UnprocessableEntity -> failure(response)
        HttpStatusCode.OK, HttpStatusCode.Created -> success(response)
        else -> failure(response)
    }
}

suspend inline fun <reified F> failure(response: HttpResponse) =
    response.body<ResponseModel<F>>().left()

suspend inline fun <reified S> success(response: HttpResponse) =
    response.body<ResponseModel<S>>().right()
