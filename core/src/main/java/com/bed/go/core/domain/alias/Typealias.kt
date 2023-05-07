package com.bed.go.core.domain.alias

import arrow.core.Either

import com.bed.go.core.domain.entities.Response
import com.bed.go.core.domain.entities.auth.SignUp
import com.bed.go.core.domain.entities.message.Message

typealias SignUpType = Either<Response<Message>, Response<SignUp>>
