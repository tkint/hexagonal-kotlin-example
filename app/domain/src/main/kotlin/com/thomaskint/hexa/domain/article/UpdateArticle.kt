package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.shared.Answer
import com.thomaskint.hexa.domain.shared.AppError
import com.thomaskint.hexa.domain.shared.exception
import com.thomaskint.hexa.domain.trace.SaveTrace
import com.thomaskint.hexa.domain.user.UserId

class UpdateArticle(
    private val port: ArticlePort,
    private val saveTrace: SaveTrace,
) {
    operator fun invoke(id: ArticleId, input: ArticleInput): Answer<Article> = Answer {
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
