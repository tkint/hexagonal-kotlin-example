package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.testUtils.shouldBeSuccess
import org.junit.jupiter.api.Test

class GetArticlesTest {
    private val articlePort = InMemoryArticleAdapter()
    private val getArticles = GetArticles(articlePort)

    private val articles = (1..5).map { index ->
        Article(
            id = ArticleId.next(),
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
