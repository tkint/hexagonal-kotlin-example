package com.thomaskint.hexa.infra.shared

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import com.thomaskint.hexa.infra.article.ArticleDb
import com.thomaskint.hexa.infra.conf.ConfValueDb
import com.thomaskint.hexa.infra.trace.TraceDb
import org.bson.UuidRepresentation
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class Database(uri: String) {
    private val client: MongoClient
    private val mongodb: MongoDatabase

    init {
        val connectionString = ConnectionString(uri)

        val name = connectionString.database ?: "hexa-db"

        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .build()

        client = KMongo.createClient(settings)
        mongodb = client.getDatabase(name)
    }

    fun disconnect() {
        client.close()
    }

    val colConf = mongodb.getCollection<ConfValueDb>("conf")

    val colTraces = mongodb.getCollection<TraceDb>("traces")
    val colArticles = mongodb.getCollection<ArticleDb>("articles")
}
