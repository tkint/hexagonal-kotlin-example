package com.thomaskint.hexa.infra.conf

import com.thomaskint.hexa.domain.conf.ConfValue
import org.bson.codecs.pojo.annotations.BsonId

data class ConfValueDb(
    @BsonId
    val key: String,
    val value: String,
) {
    fun toDomain(): ConfValue = ConfValue(
        key = key,
        value = value,
    )
}
