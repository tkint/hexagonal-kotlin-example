package com.thomaskint.hexa.domain.article

interface ArticlePort {
    fun getOne(id: com.thomaskint.hexa.domain.article.ArticleId): com.thomaskint.hexa.domain.article.Article?
    fun getAll(): List<com.thomaskint.hexa.domain.article.Article>
    fun save(article: com.thomaskint.hexa.domain.article.Article)
}
