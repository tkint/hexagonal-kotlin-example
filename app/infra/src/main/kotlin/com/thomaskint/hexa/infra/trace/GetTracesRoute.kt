package com.thomaskint.hexa.infra.trace

import com.thomaskint.hexa.domain.shared.value
import com.thomaskint.hexa.domain.trace.GetTraces
import com.thomaskint.hexa.infra.shared.Lenses.traceType
import com.thomaskint.hexa.infra.shared.Lenses.tracesLens
import com.thomaskint.hexa.infra.shared.RouteWithContract
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.meta
import org.http4k.core.*
import org.http4k.lens.Query
import org.http4k.lens.string

class GetTracesRoute(
    private val getTraces: GetTraces,
) : RouteWithContract {
    private val path = "traces"
    private val method = Method.GET

    private val typeLens = Query.traceType().optional("type")
    private val refLens = Query.string().optional("ref")

    override fun contract(): ContractRoute = path meta {
        tags += Tag("Traces")
        queries += typeLens
        queries += refLens
    } bindContract method to handler()

    fun handler(): HttpHandler = { request ->
        val type = typeLens(request)
        val ref = refLens(request)

        val traces = getTraces(type, ref).value()

        Response(Status.OK)
            .with(tracesLens of traces)
    }
}
