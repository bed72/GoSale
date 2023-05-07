package com.bed.go.sale.data.models.response.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.bed.go.core.domain.entities.auth.SignUp

@Serializable
data class SignUpResponseModel(
    @SerialName("user")
    val user: SignUpUserResponseModel
)

@Serializable
data class SignUpUserResponseModel(
    @SerialName("email")
    val email: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("user_metadata")
    val userMetaData: SignUpUserMetadataResponseModel
)

@Serializable
data class SignUpUserMetadataResponseModel(
    @SerialName("name")
    val name: String
)

fun SignUpResponseModel.toMapper() = SignUp(
    email = user.email,
    phone = user.phone,
    name = user.userMetaData.name,
)
