package com.thomaskint.hexa.domain.conf

interface ConfPort {
    fun list(): List<ConfValue>
    fun value(key: String): ConfValue?
}

fun ConfPort.string(key: String): String? =
    value(key)?.value

fun ConfPort.stringOrDefault(key: String, default: String): String =
    string(key) ?: default

fun ConfPort.int(key: String): Int? =
    string(key)?.toInt()

fun ConfPort.intOrDefault(key: String, default: Int): Int =
    int(key) ?: default

fun ConfPort.boolean(key: String): Boolean? =
    string(key)?.toBooleanStrict()

fun ConfPort.booleanOrDefault(key: String, default: Boolean): Boolean =
    boolean(key) ?: default
