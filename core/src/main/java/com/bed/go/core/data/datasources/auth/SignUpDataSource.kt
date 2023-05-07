package com.bed.go.core.data.datasources.auth

import com.bed.go.core.domain.alias.SignUpType

interface SignUpDataSource {
    suspend operator fun invoke(name: String, email: String, password: String): SignUpType
}
