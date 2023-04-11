package com.thomaskint.hexa.domain.trace

import com.thomaskint.hexa.domain.testUtils.shouldBeSuccess
import com.thomaskint.hexa.domain.user.UserId
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class GetTracesTest {
    private val tracePort = InMemoryTraceAdapter()
    private val getTraces = GetTraces(tracePort)

    private val ref1 = "ref1"
    private val ref2 = "ref2"

    private val trace1 = Trace(
        type = TraceType.ARTICLE,
        ref = ref1,
        userId = UserId.next(),
        timestamp = LocalDateTime.now(),
        changes = emptyMap(),
    )
    private val trace2 = trace1.copy(
        ref = ref1,
    )
    private val trace3 = trace1.copy(
        ref = ref2,
    )

    init {
        tracePort assumeTracesExists listOf(trace1, trace2, trace3)
    }

    @Test
    fun `returns traces when found`() {
        getTraces(null, null) shouldBeSuccess listOf(trace1, trace2, trace3)
    }

    @Test
    fun `returns traces when found with type`() {
        getTraces(TraceType.ARTICLE, null) shouldBeSuccess listOf(trace1, trace2, trace3)
    }

    @Test
    fun `returns traces when found with ref`() {
        getTraces(TraceType.ARTICLE, ref1) shouldBeSuccess listOf(trace1, trace2)
    }

    @Test
    fun `returns traces when found with ref2`() {
        getTraces(TraceType.ARTICLE, ref2) shouldBeSuccess listOf(trace3)
    }
}
