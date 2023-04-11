package com.thomaskint.hexa.infra.conf

import com.thomaskint.hexa.infra.shared.Database
import org.litote.kmongo.findOneById
import org.litote.kmongo.save

class ConfRepository(private val database: Database) {
    fun findByKey(key: String): ConfValueDb? {
        return database.colConf.findOneById(key)
    }

    fun listAll(): List<ConfValueDb> {
        return database.colConf.find().toList()
    }

    fun save(conf: ConfValueDb) {
        database.colConf.save(conf)
    }
}
