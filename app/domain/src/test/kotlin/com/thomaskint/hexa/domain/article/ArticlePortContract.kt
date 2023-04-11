package com.thomaskint.hexa.domain.article

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class InMemoryArticleAdapter : ArticlePort {
    private val data: MutableMap<ArticleId, Article> = mutableMapOf()

    override fun getOne(id: ArticleId): Article? = data[id]

    override fun getAll(): List<Article> = data.values.toList()

    override fun save(article: Article) {
        data[article.id] = article
    }
}

infix fun InMemoryArticleAdapter.assumeArticleExists(article: Article) {
    save(article)
}

infix fun InMemoryArticleAdapter.assumeArticlesExists(articles: Collection<Article>) {
    articles.forEach { save(it) }
}

abstract class ArticlePortContract {
    abstract val port: ArticlePort

    @Test
    fun `test contract`() {
        port.getAll() shouldBe emptyList()

        val articleId = ArticleId.next()

        port.getOne(articleId) shouldBe null

        val article = Article(
            id = articleId,
            label = "Label article",
        )

        port.save(article)

        port.getAll() shouldBe listOf(article)

        port.getOne(articleId) shouldBe article

        val unknownId = ArticleId.next()

        port.getOne(unknownId) shouldBe null
    }
}

class InMemoryArticleAdapterTest : ArticlePortContract() {
    override val port: ArticlePort = InMemoryArticleAdapter()
}
