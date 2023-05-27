package com.bed.go.core.domain.validators

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals

import kotlinx.coroutines.test.runTest

import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class NameValidatorTest {
    @Test
    fun `Should return messages failure when Name is invalid`() = runTest {
        val data = NameValidator("")

        assertTrue(data.isLeft())
        data.mapLeft { message ->
            assertTrue(message.contains("Preencha um nome e um sobrenome válidos."))
            assertTrue(message.contains("O nome e o sobrenome precisam ser válidos."))
        }
    }

    @Test
    fun `Should return message failure when Name is invalid with partial validations`() = runTest {
        val data = NameValidator("Ga")

        assertTrue(data.isLeft())
        data.mapLeft { assertTrue(it.contains("O nome e o sobrenome precisam ser válidos.")) }
    }

    @Test
    fun `Should return the Name when value is valid`() = runTest {
        val data = NameValidator("Gabriel Ramos")

        assertTrue(data.isRight())
        data.map { assertEquals(it, "Gabriel Ramos") }
    }
}
