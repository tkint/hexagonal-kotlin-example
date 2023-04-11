package com.thomaskint.hexa.infra.article

import com.thomaskint.hexa.domain.article.ArticleId
import com.thomaskint.hexa.domain.article.GetArticle
import com.thomaskint.hexa.domain.shared.value
import com.thomaskint.hexa.infra.shared.Lenses.articleId
import com.thomaskint.hexa.infra.shared.Lenses.articleLens
import com.thomaskint.hexa.infra.shared.RouteWithContract
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.div
import org.http4k.contract.meta
import org.http4k.core.*
import org.http4k.lens.Path

class GetArticleRoute(
    private val getArticle: GetArticle,
) : RouteWithContract {
    private val idLens = Path.articleId().of("id")

    private val path = "article" / idLens
    private val method = Method.GET

    override fun contract(): ContractRoute = path meta {
        tags += Tag("Articles")
    } bindContract method to handler()

    fun handler(): (ArticleId) -> HttpHandler = { id ->
        {
            val article = getArticle(id).value()

            Response(Status.OK)
                .with(articleLens of article)
        }
    }
}
