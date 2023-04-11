package com.thomaskint.hexa.infra.conf

import com.thomaskint.hexa.domain.conf.ConfPort
import com.thomaskint.hexa.domain.conf.ConfValue
import com.thomaskint.hexa.infra.cache.CachedData
import java.time.Duration

class ConfAdapter(
    private val repository: ConfRepository,
) : ConfPort, CachedData<List<ConfValue>>(Duration.ofMinutes(15)) {
    override fun list(): List<ConfValue> {
        return data
    }

    override fun value(key: String): ConfValue? {
        return data.firstOrNull { it.key == key }
    }

    override fun load(): List<ConfValue> {
        return repository.listAll().map { it.toDomain() }
    }
}
