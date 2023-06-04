package com.bed.go.core.domain.params

import arrow.core.Either

interface Params<out T> {
    fun isValid(): Either<String?, T>
}