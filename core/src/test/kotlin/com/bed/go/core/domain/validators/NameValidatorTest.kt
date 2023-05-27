package com.bed.go.core.domain.validators

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals

import kotlinx.coroutines.test.runTest

import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class NameValidatorTest {
    @Test
    fun `Should return message failure when Name is invalid`() = runTest {
        val data = NameValidator("")

        data.mapLeft { message ->
            assertEquals(message, "Preencha nome e o sobrenome válidos.,O nome e o sobrenome precisam ser válidos.")
        }
    }

    @Test
    fun `Should return the Name when value is valid`() = runTest {
        val data = NameValidator("Gabriel Ramos")

        data.map { name ->
            assertEquals(name, "Gabriel Ramos")
        }
    }
}