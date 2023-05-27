package com.bed.go.sale.framework.modules.confiurations

import javax.inject.Singleton

import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

import com.bed.go.sale.framework.configurations.http.installLogging
import com.bed.go.sale.framework.configurations.http.installRequest
import com.bed.go.sale.framework.configurations.http.installResponseObserver
import com.bed.go.sale.framework.configurations.http.installContentNegotiation

@Module
@InstallIn(SingletonComponent::class)
object HttpConfigurationModule {
    @Provides
    @Singleton
    fun providesHttpConfiguration() = HttpClient(OkHttp) {
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
