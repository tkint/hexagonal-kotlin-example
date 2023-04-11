package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.shared.Answer
import com.thomaskint.hexa.domain.shared.throwIt
import com.thomaskint.hexa.domain.trace.SaveTrace
import com.thomaskint.hexa.domain.user.UserId

class CreateArticle(
    private val port: ArticlePort,
    private val saveTrace: SaveTrace,
) {
    operator fun invoke(input: ArticleInput): Answer<Article> = Answer {
        input.validate().throwIt()

        val article = Article(
            id = ArticleId.next(),
            label = input.label,
        )

        port.save(article)

        saveTrace(UserId.next(), oldValue = null, newValue = article)

        article
    }
}
