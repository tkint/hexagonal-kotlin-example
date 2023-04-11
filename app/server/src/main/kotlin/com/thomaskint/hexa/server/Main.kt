package com.thomaskint.hexa.server

import com.thomaskint.hexa.infra.shared.CatchException
import com.thomaskint.hexa.infra.shared.RouteWithContract
import org.http4k.cloudnative.env.Environment
import org.http4k.contract.contract
import org.http4k.contract.openapi.ApiInfo
import org.http4k.contract.openapi.v3.ApiServer
import org.http4k.contract.openapi.v3.OpenApi3
import org.http4k.contract.ui.swaggerUiLite
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.format.Jackson
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer
import org.koin.core.context.startKoin

fun main() {
    val environment = Environment.fromResource("application.properties")

    val app = startKoin {
        val modules = Configuration(environment)

        modules(
            modules.infraModule,
            modules.domainModule,
            modules.routesModule,
        )
    }

    val apiDescriptionPath = "/docs/openapi.json"
    val api = contract {
        renderer = OpenApi3(
            apiInfo = ApiInfo("Hexagonal example", ""),
            json = Jackson,
            servers = listOf(
                ApiServer(
                    url = Uri.of("http://localhost:8080"),
                    description = """
                    """.trimIndent()
                ),
            ),
        )
        descriptionPath = apiDescriptionPath

        routes += app.koin.getAll<RouteWithContract>().map(RouteWithContract::contract)
    }

    val ui = swaggerUiLite {
        url = apiDescriptionPath
        pageTitle = "Hexagonal example"
        displayOperationId = true
    }

    val handler = routes(
        "/" bind api,
        "/swagger/" bind ui,
    )

    CatchException()
        .then(handler)
        .asServer(Undertow(8080))
        .start()
        .also {
            println("Server started : http://localhost:${it.port()}")
            println("Swagger : http://localhost:${it.port()}/swagger/")
        }
}
