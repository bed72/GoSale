package com.bed.go.core.data.datasources.authentication

import com.bed.go.core.domain.alias.SignUpType
import com.bed.go.core.domain.params.authentication.SignUpParams

interface SignUpDataSource {
    suspend operator fun invoke(params: SignUpParams): SignUpType
}
