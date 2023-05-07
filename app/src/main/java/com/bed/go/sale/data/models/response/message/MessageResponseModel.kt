package com.bed.go.sale.data.models.response.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.bed.go.core.domain.entities.message.Message

@Serializable
data class MessageResponseModel(
    @SerialName("message")
    val message: String,
)

fun MessageResponseModel.toMapper() = Message(message)
