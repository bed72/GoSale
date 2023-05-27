package com.bed.go.core.domain.params.authentication

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.zipOrAccumulate
import com.bed.go.core.domain.params.Params

import com.bed.go.core.domain.validators.Validators
import com.bed.go.core.domain.validators.NameValidator
import com.bed.go.core.domain.validators.EmailValidator
import com.bed.go.core.domain.validators.PasswordValidator

data class SignUpParams(
    val name: String,
    val email: String,
    val password: String
) : Params<SignUpParams> {
    override fun isValid(): Either<String?, SignUpParams> = either {
        val nameIsValid = NameValidator(name)
        val emailIsValid = EmailValidator(email)
        val passwordIsValid = PasswordValidator(password)

        zipOrAccumulate(
            { before, after -> Validators.combine(before, after) },
            { ensure(nameIsValid.isRight()) { nameIsValid.leftOrNull() } },
            { ensure(emailIsValid.isRight()) { emailIsValid.leftOrNull() } },
            { ensure(passwordIsValid.isRight()) { passwordIsValid.leftOrNull() } }
        ) { _, _, _ -> SignUpParams(name, email, password) }
    }
}
