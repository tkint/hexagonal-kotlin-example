package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.shared.Answer
import com.thomaskint.hexa.domain.shared.AppError
import com.thomaskint.hexa.domain.shared.exception

class GetArticle(private val port: com.thomaskint.hexa.domain.article.ArticlePort) {
    operator fun invoke(id: com.thomaskint.hexa.domain.article.ArticleId): Answer<com.thomaskint.hexa.domain.article.Article> = Answer {
        port.getOne(id)
            ?: throw AppError.NotFound("Article `$id` not found").exception
    }
}
