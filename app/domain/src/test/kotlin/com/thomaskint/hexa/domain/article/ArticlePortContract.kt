package com.thomaskint.hexa.domain.article

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class InMemoryArticleAdapter : com.thomaskint.hexa.domain.article.ArticlePort {
    private val data: MutableMap<com.thomaskint.hexa.domain.article.ArticleId, com.thomaskint.hexa.domain.article.Article> = mutableMapOf()

    override fun getOne(id: com.thomaskint.hexa.domain.article.ArticleId): com.thomaskint.hexa.domain.article.Article? = data[id]

    override fun getAll(): List<com.thomaskint.hexa.domain.article.Article> = data.values.toList()

    override fun save(article: com.thomaskint.hexa.domain.article.Article) {
        data[article.id] = article
    }
}

infix fun InMemoryArticleAdapter.assumeArticleExists(article: com.thomaskint.hexa.domain.article.Article) {
    save(article)
}

infix fun InMemoryArticleAdapter.assumeArticlesExists(articles: Collection<com.thomaskint.hexa.domain.article.Article>) {
    articles.forEach { save(it) }
}

abstract class ArticlePortContract {
    abstract val port: com.thomaskint.hexa.domain.article.ArticlePort

    @Test
    fun `test contract`() {
        port.getAll() shouldBe emptyList()

        val articleId = com.thomaskint.hexa.domain.article.ArticleId.next()

        port.getOne(articleId) shouldBe null

        val article = com.thomaskint.hexa.domain.article.Article(
            id = articleId,
            label = "Label article",
        )

        port.save(article)

        port.getAll() shouldBe listOf(article)

        port.getOne(articleId) shouldBe article

        val unknownId = com.thomaskint.hexa.domain.article.ArticleId.next()

        port.getOne(unknownId) shouldBe null
    }
}

class InMemoryArticleAdapterTest : ArticlePortContract() {
    override val port: com.thomaskint.hexa.domain.article.ArticlePort = InMemoryArticleAdapter()
}
