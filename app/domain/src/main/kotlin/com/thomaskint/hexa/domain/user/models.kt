package com.thomaskint.hexa.domain.user

import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.minLength
import java.util.*

@JvmInline
value class UserId private constructor(val value: UUID) {
    companion object {
        fun next(): UserId =
            UserId(UUID.randomUUID())

        fun valueOf(value: UUID): UserId =
            UserId(value)
    }

    override fun toString(): String = value.toString()
}

data class User(
    val id: UserId,
    val label: String
)

data class UserInput(
    val label: String,
) {
    companion object {
        val validator = Validation {
            UserInput::label {
                minLength(1) hint "The label must not be empty"
            }
        }
    }

    fun validate(): ValidationResult<UserInput> =
        validator(this)
}
