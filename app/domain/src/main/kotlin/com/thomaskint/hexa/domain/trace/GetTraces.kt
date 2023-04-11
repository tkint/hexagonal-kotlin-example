package com.thomaskint.hexa.domain.trace

import com.thomaskint.hexa.domain.shared.Answer

class GetTraces(private val port: TracePort) {
    operator fun invoke(type: TraceType?, ref: String?): Answer<List<Trace>> = Answer {
        port.list(type, ref)
    }
}
