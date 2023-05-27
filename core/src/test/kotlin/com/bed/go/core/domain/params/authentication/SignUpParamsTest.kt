package com.bed.go.core.domain.params.authentication

import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals

import kotlinx.coroutines.test.runTest

import org.mockito.junit.MockitoJUnitRunner

import com.bed.go.test.factories.authentication.SignUpFactory

@RunWith(MockitoJUnitRunner::class)
internal class SignUpParamsTest {

    private lateinit var factory: SignUpFactory

    @Before
    fun setUp() {
        factory = SignUpFactory()
    }

    @Test
    fun `Should return message failure when value NameVo is invalid`() = runTest {
        factory.invalidParams.isValid().mapLeft { message ->
            assertEquals(
                message,
                "O nome e o sobrenome precisam ser válidos.,O e-mail precisa ser válido.,A senha presica conter caracteres numéricos.,A senha presica conter caracteres maiúsculos."
            )
        }
    }

    @Test
    fun `Should return thw SignUpParams when value is valid`() = runTest {
        factory.validParams.isValid().map { params ->
            assertEquals(params.name, "Gabriel Ramos")
            assertEquals(params.email, "email@email.com")
            assertEquals(params.password, "P@ssw0rD")
        }
    }
}