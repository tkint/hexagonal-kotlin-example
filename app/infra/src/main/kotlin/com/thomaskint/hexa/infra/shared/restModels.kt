package com.thomaskint.hexa.infra.shared

import com.thomaskint.hexa.domain.shared.AppError

data class AppErrorRest(
    val code: Int,
    val message: String,
    val description: String?,
)

fun AppError.toRest(): AppErrorRest = AppErrorRest(
    code = code,
    message = message,
    description = description,
)
