package com.bed.go.test.factories.auth

import arrow.core.left
import arrow.core.right

import com.bed.go.core.domain.entities.Response
import com.bed.go.core.domain.entities.auth.SignUp
import com.bed.go.core.domain.entities.message.Message

import com.bed.go.core.data.usecases.auth.SignUpUseCase

class SignUpFactory {
    val params = SignUpUseCase.Params("Gabriel", "email@email.com", "P@ssw0rD")

    val failure get() = create(Mock.Failure)
    val success get() = create(Mock.Success)

    private fun create(mock: Mock) = when (mock) {
        Mock.Success -> Response(200, SignUp("Gabriel Ramos")).right()
        Mock.Failure -> Response(400, Message("Este e-mail já foi cadastrado!")).left()
    }

    sealed class Mock {
        object Success : Mock()
        object Failure : Mock()
    }

}
