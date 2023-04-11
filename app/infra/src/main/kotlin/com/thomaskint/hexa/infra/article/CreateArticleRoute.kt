package com.thomaskint.hexa.infra.article

import com.thomaskint.hexa.domain.article.ArticleInput
import com.thomaskint.hexa.domain.article.CreateArticle
import com.thomaskint.hexa.domain.shared.value
import com.thomaskint.hexa.infra.shared.Lenses.articleInputLens
import com.thomaskint.hexa.infra.shared.Lenses.articleLens
import com.thomaskint.hexa.infra.shared.RouteWithContract
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.meta
import org.http4k.core.*

class CreateArticleRoute(
    private val createArticle: CreateArticle,
) : RouteWithContract {
    private val path = "article"
    private val method = Method.POST

    override fun contract(): ContractRoute = path meta {
        tags += Tag("Articles")
        receiving(articleInputLens to example)
    } bindContract method to handler()

    fun handler(): HttpHandler = { request ->
        val input = articleInputLens(request)

        val article = createArticle(input).value()

        Response(Status.OK)
            .with(articleLens of article)
    }

    private val example = ArticleInput(
        label = "Article label"
    )
}
