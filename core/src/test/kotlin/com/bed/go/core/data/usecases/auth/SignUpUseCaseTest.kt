package com.bed.go.core.data.usecases.auth

import org.junit.Rule
import org.junit.Test
import org.junit.Before
import org.mockito.Mock

import arrow.core.Either

import org.junit.runner.RunWith

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever

import org.mockito.junit.MockitoJUnitRunner

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi

import com.bed.go.test.rule.MainCoroutineRule
import com.bed.go.test.factories.auth.SignUpFactory

import com.bed.go.core.domain.entities.Response
import com.bed.go.core.domain.entities.auth.SignUp
import com.bed.go.core.domain.entities.message.Message

import com.bed.go.core.data.repositories.auth.SignUpRepository

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class SignUpUseCaseTest {
    @get:Rule
    val rule = MainCoroutineRule()

    private lateinit var useCase: SignUpUseCase

    private lateinit var factory: SignUpFactory

    @Mock
    private lateinit var repository: SignUpRepository

    @Before
    fun setUp() {
        factory = SignUpFactory()
        useCase = SignUpUseCaseImpl(rule.dispatcher, repository)
    }

    @Test
    fun `Should return value not null when trying create account`() = runTest {
        whenever(repository(any(), any(), any())).thenReturn(factory.failure)

        val response = useCase(factory.params).first()

        TestCase.assertNotNull(response)
    }

    @Test
    fun `Should only call repository once when trying create account`() = runTest {
        whenever(repository(any(), any(), any())).thenReturn(factory.failure)

        useCase(factory.params).first()

        verify(repository, times(1))(any(), any(), any())
    }

    @Test
    fun `Should return failure value when trying a create account`() = runTest {
        whenever(repository(any(), any(), any())).thenReturn(factory.failure)

        val response = useCase(factory.params).first()

        TestCase.assertTrue(response is Either.Left<Response<Message>>)
    }

    @Test
    fun `Should return failure value with status and message when trying a create account`() = runTest {
        whenever(repository(any(), any(), any())).thenReturn(factory.failure)

        val response = useCase(factory.params).first()

        response.onLeft { (status, data) ->
            assertEquals(status, 400)
            assertEquals(data.message, "Este e-mail já foi cadastrado!")
        }
    }

    @Test
    fun `Should return success value when trying a create account`() = runTest {
        whenever(repository(any(), any(), any())).thenReturn(factory.success)

        val response = useCase(factory.params).first()

        TestCase.assertTrue(response is Either.Right<Response<SignUp>>)
    }

    @Test
    fun `Should return success value with status and message when trying a create account`() = runTest {
        whenever(repository(any(), any(), any())).thenReturn(factory.success)

        val response = useCase(factory.params).first()

        response.onRight { (status, data) ->
            assertEquals(status, 200)
            assertEquals(data.name, "Gabriel Ramos")
        }
    }
}
