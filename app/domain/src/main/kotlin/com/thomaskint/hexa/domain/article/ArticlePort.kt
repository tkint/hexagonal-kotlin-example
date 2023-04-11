package com.thomaskint.hexa.domain.article

interface ArticlePort {
    fun getOne(id: ArticleId): Article?
    fun getAll(): List<Article>
    fun save(article: Article)
}
