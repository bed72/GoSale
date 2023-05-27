package com.bed.go.test.factories.authentication

import arrow.core.left
import arrow.core.right

import com.bed.go.core.domain.entities.message.Message
import com.bed.go.core.domain.entities.authentication.SignUp
import com.bed.go.core.domain.params.authentication.SignUpParams

class SignUpFactory {
    val invalidParams = SignUpParams("...", "...", "...")
    val validParams = SignUpParams("Gabriel Ramos", "email@email.com", "P@ssw0rD")
    val failure get() = create(Mock.Failure)
    val success get() = create(Mock.Success)

    private fun create(mock: Mock) = when (mock) {
        Mock.Success -> SignUp("Bed", "email@email.com", "P@ssw0rD").right()
        Mock.Failure -> Message("Este e-mail já foi cadastrado!").left()
    }

    sealed class Mock {
        object Success : Mock()
        object Failure : Mock()
    }

}
