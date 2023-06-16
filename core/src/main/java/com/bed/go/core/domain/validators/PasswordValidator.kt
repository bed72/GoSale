package com.bed.go.core.domain.validators

import java.util.regex.Pattern

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.zipOrAccumulate

private val patternNeedsNumberCharacter get() = Pattern.compile(/* regex = */ ".*\\d.*")

private val patternNeedsUpperCaseCharacter get() = Pattern.compile(/* regex = */ ".*[A-Z].*")


@JvmInline
value class PasswordValidator private constructor(private val value: String) {
    companion object : Validators<String> {
        override fun invoke(value: String): Either<String, String> = either {
            zipOrAccumulate(
                { before, after -> Validators.combine(before, after) },
                { ensure(value.isNotEmpty()) { "Preencha uma senha válida." } },
                {
                    ensure(patternNeedsNumberCharacter.matcher(value).matches()) {
                        "A senha presica conter caracteres numéricos."
                    }
                },
                {
                    ensure(patternNeedsUpperCaseCharacter.matcher(value).matches()) {
                        "A senha presica conter caracteres maiúsculos."
                    }
                }
            ) { _, _, _ -> value }
        }
    }
}
