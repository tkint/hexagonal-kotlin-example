package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.shared.Answer
import com.thomaskint.hexa.domain.shared.AppError
import com.thomaskint.hexa.domain.shared.exception
import com.thomaskint.hexa.domain.trace.SaveTrace
import com.thomaskint.hexa.domain.user.UserId

class UpdateArticle(
    private val port: com.thomaskint.hexa.domain.article.ArticlePort,
    private val saveTrace: SaveTrace,
) {
    operator fun invoke(id: com.thomaskint.hexa.domain.article.ArticleId, input: com.thomaskint.hexa.domain.article.ArticleInput): Answer<com.thomaskint.hexa.domain.article.Article> = Answer {
        val oldArticle = port.getOne(id)
            ?: throw AppError.NotFound("Article `$id` not found").exception

        val newArticle = oldArticle.copy(
            label = input.label,
        )

        port.save(newArticle)

        saveTrace(UserId.next(), oldValue = oldArticle, newValue = newArticle)

        newArticle
    }
}
