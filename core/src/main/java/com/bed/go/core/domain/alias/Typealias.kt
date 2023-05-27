package com.bed.go.core.domain.alias

import arrow.core.Either

import com.bed.go.core.domain.entities.message.Message
import com.bed.go.core.domain.entities.authentication.SignUp

typealias SignUpType = Either<Message, SignUp>
