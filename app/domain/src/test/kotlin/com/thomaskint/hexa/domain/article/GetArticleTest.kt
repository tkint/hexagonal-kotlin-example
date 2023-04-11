package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.shared.AppError
import com.thomaskint.hexa.domain.testUtils.shouldBeFailure
import com.thomaskint.hexa.domain.testUtils.shouldBeSuccess
import org.junit.jupiter.api.Test

class GetArticleTest {
    private val articlePort = InMemoryArticleAdapter()
    private val getArticle = GetArticle(articlePort)

    private val article = Article(
        id = ArticleId.next(),
        label = "Label article"
    )

    init {
        articlePort assumeArticleExists article
    }

    @Test
    fun `returns article when found`() {
        getArticle(article.id) shouldBeSuccess article
    }

    @Test
    fun `returns failure when not found`() {
        val articleId = ArticleId.next()
        getArticle(articleId) shouldBeFailure AppError.NotFound("Article `$articleId` not found")
    }
}
