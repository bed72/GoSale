package com.bed.go.core.data.repositories.auth

import com.bed.go.core.domain.alias.SignUpType

interface SignUpRepository {
    suspend operator fun invoke(name: String, email: String, password: String): SignUpType
}
