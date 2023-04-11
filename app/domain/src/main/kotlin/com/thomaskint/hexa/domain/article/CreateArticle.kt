package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.shared.Answer
import com.thomaskint.hexa.domain.shared.throwIt
import com.thomaskint.hexa.domain.trace.SaveTrace
import com.thomaskint.hexa.domain.user.UserId

class CreateArticle(
    private val port: com.thomaskint.hexa.domain.article.ArticlePort,
    private val saveTrace: SaveTrace,
) {
    operator fun invoke(input: com.thomaskint.hexa.domain.article.ArticleInput): Answer<com.thomaskint.hexa.domain.article.Article> = Answer {
        input.validate().throwIt()

        val article = com.thomaskint.hexa.domain.article.Article(
            id = com.thomaskint.hexa.domain.article.ArticleId.Companion.next(),
            label = input.label,
        )

        port.save(article)

        saveTrace(UserId.next(), oldValue = null, newValue = article)

        article
    }
}
