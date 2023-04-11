package com.thomaskint.hexa.domain.user

interface UserPort {
    fun getOne(id: UserId): User?
    fun getAll(): List<User>
    fun save(user: User)
}
