package com.thomaskint.hexa.infra.shared

import com.thomaskint.hexa.domain.shared.toAppException
import org.http4k.core.*

class CatchException : Filter {
    override fun invoke(next: HttpHandler): HttpHandler = { request ->
        try {
            next(request)
        } catch (e: Exception) {
            Response(Status.INTERNAL_SERVER_ERROR)
                .with(Lenses.errorLens of e.toAppException().error)
        }
    }
}
