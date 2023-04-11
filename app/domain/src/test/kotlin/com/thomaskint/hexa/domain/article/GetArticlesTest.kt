package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.testUtils.shouldBeSuccess
import org.junit.jupiter.api.Test

class GetArticlesTest {
    private val articlePort = InMemoryArticleAdapter()
    private val getArticles = com.thomaskint.hexa.domain.article.GetArticles(articlePort)

    private val articles = (1..5).map { index ->
        com.thomaskint.hexa.domain.article.Article(
            id = com.thomaskint.hexa.domain.article.ArticleId.next(),
            label = "Label article $index"
        )
    }

    init {
        articlePort assumeArticlesExists articles
    }

    @Test
    fun `returns articles when found`() {
        getArticles() shouldBeSuccess articles
    }
}
