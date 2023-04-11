package com.thomaskint.hexa.infra.article

import com.thomaskint.hexa.domain.article.GetArticles
import com.thomaskint.hexa.domain.shared.value
import com.thomaskint.hexa.infra.shared.Lenses.articlesLens
import com.thomaskint.hexa.infra.shared.RouteWithContract
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.meta
import org.http4k.core.*

class GetArticlesRoute(
    private val getArticles: GetArticles,
) : RouteWithContract {
    private val path = "articles"
    private val method = Method.GET

    override fun contract(): ContractRoute = path meta {
        tags += Tag("Articles")
    } bindContract method to handler()

    fun handler(): HttpHandler = {
        val articles = getArticles().value()

        Response(Status.OK)
            .with(articlesLens of articles)
    }
}
