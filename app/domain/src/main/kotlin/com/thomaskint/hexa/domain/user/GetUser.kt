package com.thomaskint.hexa.domain.user

import com.thomaskint.hexa.domain.shared.Answer
import com.thomaskint.hexa.domain.shared.AppError
import com.thomaskint.hexa.domain.shared.exception

class GetUser(private val port: UserPort) {
    operator fun invoke(id: UserId): Answer<User> = Answer {
        port.getOne(id)
            ?: throw AppError.NotFound("User `$id` not found").exception
    }
}
