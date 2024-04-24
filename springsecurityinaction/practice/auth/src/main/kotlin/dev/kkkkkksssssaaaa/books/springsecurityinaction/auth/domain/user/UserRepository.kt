package dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}