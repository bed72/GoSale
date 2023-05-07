package com.bed.go.sale.framework.modules.adapters

import javax.inject.Singleton

import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

import com.bed.go.sale.framework.adapters.http.installLogging
import com.bed.go.sale.framework.adapters.http.installRequest
import com.bed.go.sale.framework.adapters.http.installResponseObserver
import com.bed.go.sale.framework.adapters.http.installContentNegotiation

@Module
@InstallIn(SingletonComponent::class)
object HttpAdapterModule {

    @Provides
    @Singleton
    fun providesHttpAdapter() = HttpClient(OkHttp) {
        installLogging()
        installRequest()
        installResponseObserver()
        installContentNegotiation()

        engine {
            config {
                protocols(listOf(Protocol.HTTP_1_1, Protocol.HTTP_2))
            }

            addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            )
        }
    }
}