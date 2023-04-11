package com.thomaskint.hexa.infra.trace

import com.thomaskint.hexa.domain.trace.TracePort
import com.thomaskint.hexa.domain.trace.TracePortContract
import com.thomaskint.hexa.infra.testUtils.TestDatabase

class TraceAdapterTest : TracePortContract() {
    private val testDatabase = TestDatabase()
    private val repository = TraceRepository(testDatabase.database)

    override val port: TracePort = TraceAdapter(repository)
}
