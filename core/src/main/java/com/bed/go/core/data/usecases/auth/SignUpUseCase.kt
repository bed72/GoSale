package com.bed.go.core.data.usecases.auth

import javax.inject.Inject

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import com.bed.go.core.domain.alias.SignUpType

import com.bed.go.core.data.repositories.auth.SignUpRepository

import com.bed.go.core.data.usecases.UseCase
import com.bed.go.core.data.usecases.coroutines.CoroutinesUseCase

interface SignUpUseCase {
    operator fun invoke(params: Params): Flow<SignUpType>

    data class Params(val name: String, val email: String, val password: String)
}

class SignUpUseCaseImpl @Inject constructor(
    private val useCase: CoroutinesUseCase,
    private val repository: SignUpRepository,
) : SignUpUseCase, UseCase<SignUpUseCase.Params, SignUpType>() {
    override suspend fun doWork(params: SignUpUseCase.Params): SignUpType =
        withContext(useCase.io()) {
            repository(params.name, params.email, params.password)
        }
}
