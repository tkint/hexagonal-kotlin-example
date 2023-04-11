package com.thomaskint.hexa.server

import com.thomaskint.hexa.domain.article.*
import com.thomaskint.hexa.domain.conf.ConfPort
import com.thomaskint.hexa.domain.trace.GetTraces
import com.thomaskint.hexa.domain.trace.SaveTrace
import com.thomaskint.hexa.domain.trace.TracePort
import com.thomaskint.hexa.infra.article.*
import com.thomaskint.hexa.infra.conf.ConfAdapter
import com.thomaskint.hexa.infra.conf.ConfRepository
import com.thomaskint.hexa.infra.shared.Database
import com.thomaskint.hexa.infra.shared.RouteWithContract
import com.thomaskint.hexa.infra.trace.GetTracesRoute
import com.thomaskint.hexa.infra.trace.TraceAdapter
import com.thomaskint.hexa.infra.trace.TraceRepository
import org.http4k.cloudnative.env.Environment
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

class Configuration(environment: Environment) {
    val infraModule = module {
        single {
            Database(
                uri = environment["db.uri"]
                    ?: throw Exception("Missing conf: `db.uri`"),
            )
        }

        singleOf(::ConfRepository)
        singleOf(::ConfAdapter) bind ConfPort::class

        singleOf(::ArticleRepository)
        singleOf(::ArticleAdapter) bind com.thomaskint.hexa.domain.article.ArticlePort::class

        singleOf(::TraceRepository)
        singleOf(::TraceAdapter) bind TracePort::class
    }

    val domainModule = module {
        singleOf(::GetArticle)
        singleOf(::GetArticles)
        singleOf(::CreateArticle)
        singleOf(::UpdateArticle)

        singleOf(::SaveTrace)
        singleOf(::GetTraces)
    }

    val routesModule = module {
        singleOf(::GetArticlesRoute) bind RouteWithContract::class
        singleOf(::GetArticleRoute) bind RouteWithContract::class
        singleOf(::CreateArticleRoute) bind RouteWithContract::class
        singleOf(::UpdateArticleRoute) bind RouteWithContract::class

        singleOf(::GetTracesRoute) bind RouteWithContract::class
    }
}
