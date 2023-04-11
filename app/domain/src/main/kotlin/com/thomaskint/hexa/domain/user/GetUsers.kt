package com.thomaskint.hexa.domain.user

import com.thomaskint.hexa.domain.shared.Answer

class GetUsers(private val port: UserPort) {
    operator fun invoke(): Answer<List<User>> = Answer {
        port.getAll()
    }
}
