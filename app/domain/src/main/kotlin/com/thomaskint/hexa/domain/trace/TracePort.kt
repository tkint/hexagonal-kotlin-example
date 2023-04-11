package com.thomaskint.hexa.domain.trace

interface TracePort {
    fun list(type: TraceType?, ref: String?): List<Trace>
    fun save(trace: Trace)
}
