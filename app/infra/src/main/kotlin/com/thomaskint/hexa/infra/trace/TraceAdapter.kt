package com.thomaskint.hexa.infra.trace

import com.thomaskint.hexa.domain.trace.Trace
import com.thomaskint.hexa.domain.trace.TracePort
import com.thomaskint.hexa.domain.trace.TraceType

class TraceAdapter(private val repository: TraceRepository) : TracePort {
    override fun list(type: TraceType?, ref: String?): List<Trace> {
        return repository.list(type, ref)
    }

    override fun save(trace: Trace) {
        repository.save(trace)
    }
}
