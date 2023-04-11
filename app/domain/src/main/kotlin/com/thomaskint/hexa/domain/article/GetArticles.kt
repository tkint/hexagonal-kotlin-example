package com.thomaskint.hexa.domain.article

import com.thomaskint.hexa.domain.shared.Answer

class GetArticles(private val port: ArticlePort) {
    operator fun invoke(): Answer<List<Article>> = Answer {
        port.getAll()
    }
}
