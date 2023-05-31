package com.bed.go.core.domain.validators

import arrow.core.Either

sealed interface Validators<out T> {
    operator fun invoke(value: String): Either<String, T>

    companion object {
        fun combine(before: String?, after: String?): String {
            if (before == null || after == null) return ""

            return if (before == after) before else "$before,$after"
        }
    }
}
