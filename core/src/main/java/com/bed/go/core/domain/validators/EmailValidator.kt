package com.bed.go.core.domain.validators

import java.util.regex.Pattern

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.zipOrAccumulate

private val pattern = Pattern.compile(/* regex = */ "^[a-zA-Z\\d+_.-]+@[a-zA-Z\\d.-]+\\.[a-zA-z]{2,3}\$")

@JvmInline
value class EmailValidator private constructor(private val value: String) {
    companion object : Validators<String>  {
        override fun invoke(value: String): Either<String, String> = either {
            zipOrAccumulate(
                { before, after -> Validators.combine(before, after) },
                { ensure(value.isNotEmpty()) { "Preencha um e-mail válido." } },
                { ensure(pattern.matcher(value).matches()) { "O e-mail precisa ser válido." } }
            ) { _, _ -> value }
        }
    }
}
