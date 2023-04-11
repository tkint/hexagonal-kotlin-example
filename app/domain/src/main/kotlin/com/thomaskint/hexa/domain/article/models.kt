package com.thomaskint.hexa.domain.article

import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.minLength
import java.util.*

@JvmInline
value class ArticleId private constructor(val value: UUID) {
    companion object {
        fun next(): ArticleId =
            ArticleId(UUID.randomUUID())

        fun valueOf(value: UUID): ArticleId =
            ArticleId(value)
    }

    override fun toString(): String = value.toString()
}

data class Article(
    val id: ArticleId,
    val label: String,
)

data class ArticleInput(
    val label: String,
) {
    companion object {
        val validator = Validation {
            ArticleInput::label {
                minLength(1) hint "The label must not be empty"
            }
        }
    }

    fun validate(): ValidationResult<ArticleInput> =
        validator(this)
}
