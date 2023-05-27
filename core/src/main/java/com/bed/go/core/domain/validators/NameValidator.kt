package com.bed.go.core.domain.validators

import java.util.regex.Pattern

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.zipOrAccumulate

private val pattern: Pattern
    get() = Pattern.compile(/* regex = */ "^([a-zA-Z]{2,}\\s[a-zA-Z]+'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]+)?)")

@JvmInline
value class NameValidator private constructor(private val value: String) {
    companion object : Validators<String> {
        override fun invoke(value: String): Either<String, String> = either {
            zipOrAccumulate(
                { before, after -> Validators.combine(before, after) },
                { ensure(value.isNotEmpty()) { "Preencha um nome e um sobrenome válidos." } },
                { ensure(pattern.matcher(value).matches()) { "O nome e o sobrenome precisam ser válidos." } }
            ) { _, _ -> value }

        }
    }
}
