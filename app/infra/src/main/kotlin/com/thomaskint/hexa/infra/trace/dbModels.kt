package com.thomaskint.hexa.infra.trace

import com.thomaskint.hexa.domain.trace.Trace
import com.thomaskint.hexa.domain.trace.TraceType
import com.thomaskint.hexa.domain.user.UserId
import java.time.LocalDateTime
import java.util.*

data class TraceDb(
    val type: TraceType,
    val ref: String,
    val userId: String,
    val timestamp: LocalDateTime,
    val changes: Map<String, Trace.Change<*>>,
) {
    fun toDomain(): Trace = Trace(
        type = type,
        ref = ref,
        userId = UserId.valueOf(UUID.fromString(userId)),
        timestamp = timestamp,
        changes = changes,
    )
}

fun Trace.toDb(): TraceDb = TraceDb(
    type = type,
    ref = ref,
    userId = userId.value.toString(),
    timestamp = timestamp,
    changes = changes,
)
