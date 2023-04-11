package com.thomaskint.hexa.infra.shared

import org.http4k.contract.ContractRoute

interface RouteWithContract {
    fun contract(): ContractRoute
}
