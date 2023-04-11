package com.thomaskint.hexa.domain.trace

import com.thomaskint.hexa.domain.article.Article
import com.thomaskint.hexa.domain.user.UserId
import java.time.LocalDateTime
import kotlin.reflect.KProperty1

class SaveTrace(private val port: TracePort) {
    /**
     * ARTICLE
     */
    private val articleTracker = buildTracker {
        includeCreate = true

        register(Article::label)
    }

    operator fun invoke(userId: UserId, oldValue: Article?, newValue: Article) = saveChanges(
        type = TraceType.ARTICLE,
        identifier = newValue.id.toString(),
        tracker = articleTracker,
        userId = userId,
        oldValue = oldValue,
        newValue = newValue,
    )

    private fun <T> saveChanges(
        type: TraceType,
        identifier: String,
        tracker: Tracker<T>,
        userId: UserId,
        oldValue: T?,
        newValue: T,
    ) {
        if (oldValue != null || tracker.includeCreate) {
            val changes = tracker.mappers.mapNotNull { (property, mapper) ->
                val oldFieldValue = oldValue?.let { property.get(oldValue)?.let(mapper) }
                val newFieldValue = property.get(newValue)?.let(mapper)

                if (oldFieldValue == newFieldValue) null
                else property.name to Trace.Change(
                    oldValue = oldFieldValue,
                    newValue = newFieldValue,
                )
            }.toMap()

            if (changes.isNotEmpty()) {
                val trace = Trace(
                    type = type,
                    ref = identifier,
                    userId = userId,
                    changes = changes,
                    timestamp = LocalDateTime.now(),
                )

                port.save(trace)
            }
        }
    }

    private class Tracker<T> private constructor(
        val includeCreate: Boolean,
        val mappers: Map<KProperty1<T, *>, (Any) -> Any>,
    ) {
        class Builder<T> {
            var includeCreate: Boolean = false
            private val mappers: MutableMap<KProperty1<T, *>, (Any) -> Any> = mutableMapOf()

            fun <TValue : Any, TMappedValue : Any> register(
                property: KProperty1<T, TValue>,
                mapper: (TValue) -> TMappedValue,
            ) {
                mappers[property] = mapper as (Any) -> Any
            }

            fun <U : Any> register(property: KProperty1<T, U>) {
                register(property) { it }
            }

            fun build(): Tracker<T> = Tracker(includeCreate, mappers)
        }
    }

    private fun <T> buildTracker(fn: Tracker.Builder<T>.() -> Unit): Tracker<T> =
        Tracker.Builder<T>().apply(fn).build()
}
