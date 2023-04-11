package com.thomaskint.hexa.infra.article

import com.thomaskint.hexa.domain.article.Article
import com.thomaskint.hexa.infra.shared.Database
import org.litote.kmongo.findOneById
import org.litote.kmongo.save
import java.util.*

class ArticleRepository(private val database: Database) {
    fun findById(id: UUID): Article? {
        return database.colArticles.findOneById(id.toString())?.toDomain()
    }

    fun listAll(): List<Article> {
        return database.colArticles.find().mapTo(mutableListOf()) { it.toDomain() }
    }

    fun save(article: Article) {
        database.colArticles.save(article.toDb())
    }
}
