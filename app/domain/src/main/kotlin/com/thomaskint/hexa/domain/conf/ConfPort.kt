package com.thomaskint.hexa.domain.conf

interface ConfPort {
    fun list(): List<com.thomaskint.hexa.domain.conf.ConfValue>
    fun value(key: String): com.thomaskint.hexa.domain.conf.ConfValue?
}

fun com.thomaskint.hexa.domain.conf.ConfPort.string(key: String): String? =
    value(key)?.value

fun com.thomaskint.hexa.domain.conf.ConfPort.stringOrDefault(key: String, default: String): String =
    string(key) ?: default

fun com.thomaskint.hexa.domain.conf.ConfPort.int(key: String): Int? =
    string(key)?.toInt()

fun com.thomaskint.hexa.domain.conf.ConfPort.intOrDefault(key: String, default: Int): Int =
    int(key) ?: default

fun com.thomaskint.hexa.domain.conf.ConfPort.boolean(key: String): Boolean? =
    string(key)?.toBooleanStrict()

fun com.thomaskint.hexa.domain.conf.ConfPort.booleanOrDefault(key: String, default: Boolean): Boolean =
    boolean(key) ?: default
