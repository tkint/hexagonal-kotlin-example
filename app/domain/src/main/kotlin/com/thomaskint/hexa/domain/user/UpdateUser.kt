package com.thomaskint.hexa.domain.user

import com.thomaskint.hexa.domain.shared.Answer
import com.thomaskint.hexa.domain.shared.AppError
import com.thomaskint.hexa.domain.shared.exception

class UpdateUser(
    private val port: UserPort,
) {
    operator fun invoke(id: UserId, input: UserInput): Answer<User> = Answer {
        val oldUser = port.getOne(id)
            ?: throw AppError.NotFound("User `$id` not found").exception

        val newUser = oldUser.copy(
            label = input.label,
        )

        port.save(newUser)

        newUser
    }
}
