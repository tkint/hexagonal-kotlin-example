package com.thomaskint.hexa.domain.trace

import com.thomaskint.hexa.domain.user.UserId
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.*

class InMemoryTraceAdapter : TracePort {
    private val data: MutableList<Trace> = mutableListOf()

    override fun list(type: TraceType?, ref: String?): List<Trace> {
        return data.filter { trace ->
            (type == null || trace.type == type) &&
                    (ref == null || trace.ref == ref)
        }
    }

    override fun save(trace: Trace) {
        data.add(trace)
    }
}

infix fun InMemoryTraceAdapter.assumeTraceExists(trace: Trace) {
    save(trace)
}

infix fun InMemoryTraceAdapter.assumeTracesExists(traces: Collection<Trace>) {
    traces.forEach { save(it) }
}

abstract class TracePortContract {
    private val clock = Clock.fixed(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC), ZoneId.systemDefault())

    abstract val port: TracePort

    @Test
    fun `test contract`() {
        port.list(null, null) shouldBe emptyList()

        val trace = Trace(
            type = TraceType.ARTICLE,
            ref = "refTrace",
            userId = UserId.next(),
            timestamp = LocalDateTime.now(clock),
            changes = emptyMap(),
        )

        port.save(trace)

        port.list(null, null) shouldBe listOf(trace)

        port.list(trace.type, null) shouldBe listOf(trace)
        port.list(trace.type, "unknownRef") shouldBe emptyList()

        port.list(null, trace.ref) shouldBe listOf(trace)
    }
}

class InMemoryTraceAdapterTest : TracePortContract() {
    override val port: TracePort = InMemoryTraceAdapter()
}
