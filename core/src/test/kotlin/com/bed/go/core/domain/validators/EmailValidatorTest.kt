package com.bed.go.core.domain.validators

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals

import kotlinx.coroutines.test.runTest

import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class EmailValidatorTest {
    @Test
    fun `Should return messages failure when Email is invalid`() = runTest {
        val data = EmailValidator("")

        data.mapLeft { message ->
            assertTrue(message.contains("Preencha um e-mail válido."))
            assertTrue(message.contains("O e-mail precisa ser válido."))
        }
    }

    @Test
    fun `Should return message failure when Email is invalid with partial validations`() = runTest {
        val data = EmailValidator("email@")

        data.mapLeft { assertTrue(it.contains("O e-mail precisa ser válido.")) }
    }

    @Test
    fun `Should return the Email when value is valid`() = runTest {
        val data = EmailValidator("email@email.com")

        data.map { assertEquals(it, "email@email.com") }
    }
}
