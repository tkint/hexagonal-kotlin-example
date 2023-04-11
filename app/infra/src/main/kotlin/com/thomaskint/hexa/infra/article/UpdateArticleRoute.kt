package com.thomaskint.hexa.infra.article

import com.thomaskint.hexa.domain.article.ArticleId
import com.thomaskint.hexa.domain.article.ArticleInput
import com.thomaskint.hexa.domain.article.UpdateArticle
import com.thomaskint.hexa.domain.shared.value
import com.thomaskint.hexa.infra.shared.Lenses
import com.thomaskint.hexa.infra.shared.Lenses.articleId
import com.thomaskint.hexa.infra.shared.Lenses.articleInputLens
import com.thomaskint.hexa.infra.shared.Lenses.articleLens
import com.thomaskint.hexa.infra.shared.RouteWithContract
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.div
import org.http4k.contract.meta
import org.http4k.core.*
import org.http4k.lens.Path

class UpdateArticleRoute(
    private val updateArticle: UpdateArticle,
) : RouteWithContract {
    private val path = "article" / Path.articleId().of("id")
    private val method = Method.PUT

    override fun contract(): ContractRoute = path meta {
        tags += Tag("Articles")
        receiving(articleInputLens to example)
    } bindContract method to handler()

    fun handler(): (ArticleId) -> HttpHandler = { id ->
        { request ->
            val input = articleInputLens(request)

            val article = updateArticle(id, input).value()

            Response(Status.OK)
                .with(articleLens of article)
        }
    }

    private val example = ArticleInput(
        label = "Article label"
    )
}
