package com.bed.go.sale.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel<T>(
    @SerialName("status") val status: Int,
    @SerialName("data") val data: T
)


