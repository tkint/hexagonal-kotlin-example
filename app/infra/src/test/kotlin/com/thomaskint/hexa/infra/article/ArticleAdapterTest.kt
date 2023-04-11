package com.thomaskint.hexa.infra.article

import com.thomaskint.hexa.domain.article.ArticlePort
import com.thomaskint.hexa.domain.article.ArticlePortContract
import com.thomaskint.hexa.infra.testUtils.TestDatabase

class ArticleAdapterTest : ArticlePortContract() {
    private val testDatabase = TestDatabase()
    private val repository = ArticleRepository(testDatabase.database)

    override val port: com.thomaskint.hexa.domain.article.ArticlePort = ArticleAdapter(repository)
}
