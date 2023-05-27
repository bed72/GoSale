package com.bed.go.core.data.usecases.authentication

import javax.inject.Inject

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import com.bed.go.core.domain.alias.SignUpType
import com.bed.go.core.domain.params.authentication.SignUpParams

import com.bed.go.core.data.repositories.authentication.SignUpRepository

import com.bed.go.core.data.usecases.UseCase
import com.bed.go.core.data.usecases.coroutines.CoroutinesUseCase

interface SignUpUseCase {
    operator fun invoke(params: SignUpParams): Flow<SignUpType>
}

class SignUpUseCaseImpl @Inject constructor(
    private val useCase: CoroutinesUseCase,
    private val repository: SignUpRepository,
) : SignUpUseCase, UseCase<SignUpParams, SignUpType>() {
    override suspend fun doWork(params: SignUpParams): SignUpType =
        withContext(useCase.io()) { repository(params) }
}
