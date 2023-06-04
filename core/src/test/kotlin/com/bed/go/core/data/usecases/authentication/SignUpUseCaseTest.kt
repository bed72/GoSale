package com.bed.go.core.data.usecases.authentication

import org.junit.Rule
import org.junit.Test
import org.junit.Before
import org.mockito.Mock

import arrow.core.Either

import org.junit.runner.RunWith

import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

import org.mockito.junit.MockitoJUnitRunner

import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi

import com.bed.go.test.rule.MainCoroutineRule
import com.bed.go.test.factories.authentication.SignUpFactory

import com.bed.go.core.domain.entities.message.Message
import com.bed.go.core.domain.entities.authentication.SignUp

import com.bed.go.core.data.repositories.authentication.SignUpRepository

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
        whenever(repository(any())).thenReturn(factory.failure)

        val response = useCase(factory.validParams).first()

        assertNotNull(response)
    }

    @Test
    fun `Should only call repository once when trying create account`() = runTest {
        whenever(repository(any())).thenReturn(factory.failure)

        useCase(factory.validParams).first()

        verify(repository, times(1))(any())
    }

    @Test
    fun `Should return failure value when trying a create account`() = runTest {
        whenever(repository(any())).thenReturn(factory.failure)

        val response = useCase(factory.validParams).first()

        assertTrue(response is Either.Left<Message>)
    }

    @Test
    fun `Should return failure value with message when trying a create account`() = runTest {
        whenever(repository(any())).thenReturn(factory.failure)

        val response = useCase(factory.validParams).first()

        response.onLeft { assertEquals(it.message, "Este e-mail já foi cadastrado!") }
    }

    @Test
    fun `Should return success value when trying a create account`() = runTest {
        whenever(repository(any())).thenReturn(factory.success)

        val response = useCase(factory.validParams).first()

        assertTrue(response is Either.Right<SignUp>)
    }

    @Test
    fun `Should return success value and message when trying a create account`() = runTest {
        whenever(repository(any())).thenReturn(factory.success)

        val response = useCase(factory.validParams).first()

        response.onRight { assertEquals(it.name, "Bed") }
    }
}
