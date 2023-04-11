package com.thomaskint.hexa.domain.trace

import com.thomaskint.hexa.domain.user.UserId
import java.time.LocalDateTime

enum class TraceType {
    ARTICLE,
}

data class Trace(
    val type: TraceType,
    val ref: String,
    val userId: UserId,
    val timestamp: LocalDateTime,
    val changes: Map<String, Change<*>>,
) {
    data class Change<TValue>(
        val oldValue: TValue,
        val newValue: TValue,
    )
}
