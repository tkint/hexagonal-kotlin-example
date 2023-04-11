package com.thomaskint.hexa.domain.testUtils

import com.thomaskint.hexa.domain.shared.Answer
import com.thomaskint.hexa.domain.shared.AppError
import com.thomaskint.hexa.domain.shared.Failure
import com.thomaskint.hexa.domain.shared.Success
import io.kotest.matchers.shouldBe

infix fun <T> Answer<T>.shouldBeSuccess(value: T) {
    this shouldBe Success(value)
}

infix fun <T> Answer<T>.shouldBeFailure(reason: AppError) {
    this shouldBe Failure(reason)
}
