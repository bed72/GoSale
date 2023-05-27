package com.bed.go.sale.framework.configurations.http

import android.util.Log

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttpConfig

import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi

import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

import com.bed.go.sale.BuildConfig

fun HttpClientConfig<OkHttpConfig>.installLogging() {
    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("[KTOR CLIENT]: ", message)
            }
        }
    }
}

fun HttpClientConfig<OkHttpConfig>.installRequest() {
    install(DefaultRequest) {
        url(BuildConfig.BASE_URL)
        headers { header(HttpHeaders.ContentType, ContentType.Application.Json) }
    }
}

fun HttpClientConfig<OkHttpConfig>.installResponseObserver() {
    install(ResponseObserver) {
        onResponse { response ->
            Log.d("[KTOR HTTP RESPONSE]: ", response.body())
            Log.d("[KTOR HTTP STATUS]: ", response.status.value.toString())
        }
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun HttpClientConfig<OkHttpConfig>.installContentNegotiation() {
    install(ContentNegotiation) {
        json(
            Json {
                explicitNulls = false
                encodeDefaults = false
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
            }
        )
    }
}
