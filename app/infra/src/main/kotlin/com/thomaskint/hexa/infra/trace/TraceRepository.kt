package com.thomaskint.hexa.infra.trace

import com.thomaskint.hexa.domain.trace.Trace
import com.thomaskint.hexa.domain.trace.TraceType
import com.thomaskint.hexa.infra.shared.Database
import org.litote.kmongo.eq
import org.litote.kmongo.find
import org.litote.kmongo.save

class TraceRepository(private val database: Database) {
    fun list(type: TraceType?, ref: String?): List<Trace> {
        return database.colTraces.find(
            type?.let { TraceDb::type eq it },
            ref?.let { TraceDb::ref eq it },
        ).mapTo(mutableListOf()) { it.toDomain() }
    }

    fun save(trace: Trace) {
        database.colTraces.save(trace.toDb())
    }
}
