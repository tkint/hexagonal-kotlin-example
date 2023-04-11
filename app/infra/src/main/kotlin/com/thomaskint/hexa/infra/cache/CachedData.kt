package com.thomaskint.hexa.infra.cache

import java.time.Duration
import java.time.LocalDateTime

abstract class CachedData<T>(private val duration: Duration) {
    protected abstract fun load(): T

    protected val data: T
        get() = cachedData?.takeIf { LocalDateTime.now().isAfter(expirationDate) }
            ?: load().also {
                cachedData = it
                expirationDate = nextExpirationDate()
            }

    private var cachedData: T? = null
    private var expirationDate = nextExpirationDate()

    fun reload() {
        expirationDate = expirationDate.minus(duration.multipliedBy(2))
    }

    fun needReload(): Boolean {
        return cachedData == null || LocalDateTime.now().isAfter(expirationDate)
    }

    private fun nextExpirationDate(): LocalDateTime =
        LocalDateTime.now().plus(duration)
}
