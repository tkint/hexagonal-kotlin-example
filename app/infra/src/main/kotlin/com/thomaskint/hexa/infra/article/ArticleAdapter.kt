package com.thomaskint.hexa.infra.article

import com.thomaskint.hexa.domain.article.Article
import com.thomaskint.hexa.domain.article.ArticleId
import com.thomaskint.hexa.domain.article.ArticlePort

class ArticleAdapter(private val repository: ArticleRepository) : com.thomaskint.hexa.domain.article.ArticlePort {
    override fun getOne(id: ArticleId): Article? {
        return repository.findById(id.value)
    }

    override fun getAll(): List<Article> {
        return repository.listAll()
    }

    override fun save(article: Article) {
        repository.save(article)
    }
}
