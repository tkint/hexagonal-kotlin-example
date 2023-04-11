package com.thomaskint.hexa.infra.shared

import com.thomaskint.hexa.domain.article.Article
import com.thomaskint.hexa.domain.article.ArticleId
import com.thomaskint.hexa.domain.article.ArticleInput
import com.thomaskint.hexa.domain.shared.AppError
import com.thomaskint.hexa.domain.trace.Trace
import com.thomaskint.hexa.domain.trace.TraceType
import org.http4k.core.Body
import org.http4k.format.Jackson.auto
import org.http4k.lens.BiDiMapping
import org.http4k.lens.BiDiPathLensSpec
import org.http4k.lens.Path
import org.http4k.lens.Query
import org.http4k.lens.StringBiDiMappings.uuid
import java.util.*

object Lenses {
    val errorLens = Body.auto<AppError>().toLens()

    val articleLens = Body.auto<Article>().toLens()
    val articlesLens = Body.auto<List<Article>>().toLens()
    val articleInputLens = Body.auto<ArticleInput>().toLens()

    val tracesLens = Body.auto<List<Trace>>().toLens()

    fun Query.traceType() = map(TraceType::valueOf, TraceType::name)
    fun Path.articleId() = bidiPath(Mappings.articleId())

    private fun <T> Path.bidiPath(mapping: BiDiMapping<String, T>): BiDiPathLensSpec<T> =
        map(mapping::invoke, mapping::invoke)
}

object Mappings {
    fun articleId() = mapUUID(ArticleId::valueOf, ArticleId::value)

    private inline fun <reified T> mapUUID(crossinline mapIn: (UUID) -> T, crossinline mapOut: (T) -> UUID) =
        uuid().map(mapIn, mapOut)
}
