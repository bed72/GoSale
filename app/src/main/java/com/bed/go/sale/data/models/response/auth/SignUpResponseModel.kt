package com.bed.go.sale.data.models.response.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.bed.go.core.domain.entities.authentication.SignUp

@Serializable
data class SignUpResponseModel(
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("phone")
    val phone: String,
)

fun SignUpResponseModel.toMapper() = SignUp(
    name = name,
    email = email,
    phone = phone,
)
