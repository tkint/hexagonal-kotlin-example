package com.thomaskint.hexa.infra.testUtils

import com.thomaskint.hexa.infra.shared.Database
import de.bwaldvogel.mongo.InMemoryMongoServer
import de.bwaldvogel.mongo.MongoServer
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDatabase {
    private val server: MongoServer
    val database: Database

    init {
        server = InMemoryMongoServer()
        database = Database(server.bindAndGetConnectionString())
    }

    @AfterAll
    fun stopServer() {
        database.disconnect()
        server.shutdown()
    }
}
