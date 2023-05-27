package com.bed.go.core.data.repositories.authentication

import com.bed.go.core.domain.alias.SignUpType
import com.bed.go.core.domain.params.authentication.SignUpParams

interface SignUpRepository {
    suspend operator fun invoke(params: SignUpParams): SignUpType
}
