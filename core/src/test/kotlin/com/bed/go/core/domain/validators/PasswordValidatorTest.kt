package com.bed.go.core.domain.validators

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals

import kotlinx.coroutines.test.runTest

import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class PasswordValidatorTest {
    @Test
    fun `Should return messages failure when Password is invalid`() = runTest {
        val data = PasswordValidator("")

        assertTrue(data.isLeft())
        data.mapLeft { message ->
            assertTrue(message.contains("Preencha uma senha válida."))
            assertTrue(message.contains("A senha presica conter caracteres numéricos."))
            assertTrue(message.contains("A senha presica conter caracteres maiúsculos."))
        }
    }

    @Test
    fun `Should return messages failure when Password is invalid with partial validations`() = runTest {
        val data = PasswordValidator("b")

        assertTrue(data.isLeft())
        data.mapLeft { message ->
            assertTrue(message.contains("A senha presica conter caracteres numéricos."))
            assertTrue(message.contains("A senha presica conter caracteres maiúsculos."))
        }
    }

    @Test
    fun `Should return message failure when Password is invalid with partial validations`() = runTest {
        val data = PasswordValidator("b0")

        assertTrue(data.isLeft())
        data.mapLeft { assertTrue(it.contains("A senha presica conter caracteres maiúsculos.")) }
    }

    @Test
    fun `Should return the Password when value is valid`() = runTest {
        val data = PasswordValidator("P@ssw0rD")

        assertTrue(data.isRight())
        data.map { assertEquals(it, "P@ssw0rD") }
    }
}
