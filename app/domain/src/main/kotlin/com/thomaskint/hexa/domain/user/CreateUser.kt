package com.thomaskint.hexa.domain.user

import com.thomaskint.hexa.domain.shared.Answer
import com.thomaskint.hexa.domain.shared.throwIt

class CreateUser(
    private val port: UserPort,
) {
    operator fun invoke(input: UserInput): Answer<User> = Answer {
        input.validate().throwIt()

        val user = User(
            id = UserId.next(),
            label = input.label,
        )

        port.save(user)

        user
    }
}
