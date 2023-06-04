package com.bed.go.sale.data.alias

import arrow.core.Either

import com.bed.go.sale.data.models.response.ResponseModel
import com.bed.go.sale.data.models.response.authentication.SignUpResponseModel
import com.bed.go.sale.data.models.response.message.MessageResponseModel

typealias SignUpModelType =
        Either<ResponseModel<MessageResponseModel>, ResponseModel<SignUpResponseModel>>
