package com.thomaskint.hexa.domain.shared

import io.konform.validation.Invalid
import io.konform.validation.Valid
import io.konform.validation.ValidationErrors
import io.konform.validation.ValidationResult

data class AppException(
    val error: AppError,
) : Exception(error.message)

sealed class AppError(
    val code: Int,
    val message: String,
) {
    abstract val description: String?

    data class Unexpected(override val description: String?) :
        AppError(0, "Unexpected error")

    data class NotFound(override val description: String?) :
        AppError(1, "Not found")

    data class InvalidInput(override val description: String?, val errors: ValidationErrors) :
        AppError(2, "Invalid input")
}

fun Exception.toAppException(): AppException =
    if (this is AppException) this
    else AppError.Unexpected(message).exception

val AppError.exception get() = AppException(this)

fun AppError.throwIt(): Nothing = throw exception

fun <U> ValidationResult<U>.throwIt(description: String? = null) {
    when (this) {
        is Valid<U> -> Unit
        is Invalid<U> -> AppError.InvalidInput(description, errors).throwIt()
    }
}
