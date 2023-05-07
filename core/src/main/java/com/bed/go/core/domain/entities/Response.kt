package com.bed.go.core.domain.entities

data class Response<out T>(
    val status: Int,
    val data: T
)
