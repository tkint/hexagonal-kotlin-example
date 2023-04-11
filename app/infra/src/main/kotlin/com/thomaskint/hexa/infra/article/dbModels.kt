package com.thomaskint.hexa.infra.article

import com.thomaskint.hexa.domain.article.Article
import com.thomaskint.hexa.domain.article.ArticleId
import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

data class ArticleDb(
    @BsonId
    val id: String,
    val label: String
) {
    fun toDomain(): Article = Article(
        id = ArticleId.valueOf(UUID.fromString(id)),
        label = label,
    )
}

fun Article.toDb(): ArticleDb = ArticleDb(
    id = id.value.toString(),
    label = label,
)
