package com.thomaskint.hexa.domain.article

import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.minLength
import java.util.*

@JvmInline
value class ArticleId private constructor(val value: UUID) {
    companion object {
        fun next(): com.thomaskint.hexa.domain.article.ArticleId =
            com.thomaskint.hexa.domain.article.ArticleId(UUID.randomUUID())

        fun valueOf(value: UUID): com.thomaskint.hexa.domain.article.ArticleId =
            com.thomaskint.hexa.domain.article.ArticleId(value)
    }

    override fun toString(): String = value.toString()
}

data class Article(
    val id: com.thomaskint.hexa.domain.article.ArticleId,
    val label: String
)

data class ArticleInput(
    val label: String,
) {
    companion object {
        val validator = Validation {
            com.thomaskint.hexa.domain.article.ArticleInput::label {
                minLength(1) hint "The label must not be empty"
            }
        }
    }

    fun validate(): ValidationResult<com.thomaskint.hexa.domain.article.ArticleInput> =
        com.thomaskint.hexa.domain.article.ArticleInput.Companion.validator(this)
}
