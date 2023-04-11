package com.thomaskint.hexa.domain.shared

sealed interface Answer<out T> {
    companion object {
        operator fun <T> invoke(fn: () -> T): Answer<T> = try {
            Success(fn())
        } catch (e: Exception) {
            Failure(e.toAppException().error)
        }

//        fun <T> lazy(fn: () -> T): Answer<T> = try {
//            LazySuccess(fn)
//        } catch (e: Exception) {
//            Failure(e.toAppException().error)
//        }
//
//        fun <T> async(fn: () -> T): Answer<T> {
//            val value = CompletableFuture.supplyAsync(fn)
//            return lazy(value::get)
//        }
    }
}

data class LazySuccess<out T>(val fn: () -> T) : Answer<T> {
    val value by lazy(fn)
}

data class Success<out T>(val value: T) : Answer<T>

data class Failure(val reason: AppError) : Answer<Nothing>

fun <T> T.toSuccess(): Success<T> = Success(this)

@Throws(AppException::class)
fun <T> Answer<T>.value(): T = when (this) {
    is Success<T> -> value
    is LazySuccess<T> -> value
    is Failure -> throw AppException(reason)
}

fun <T> Answer<T>.valueOrNull(): T? = valueOr { null }

fun <T> Answer<T>.valueOr(fn: (AppError) -> T): T = when (this) {
    is Success<T> -> value
    is LazySuccess<T> -> value
    is Failure -> fn(reason)
}
