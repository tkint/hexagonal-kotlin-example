package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.shared.Answer

class GetArticles(private val port: com.thomaskint.hexa.domain.article.ArticlePort) {
    operator fun invoke(): Answer<List<com.thomaskint.hexa.domain.article.Article>> = Answer {
        port.getAll()
    }
}
