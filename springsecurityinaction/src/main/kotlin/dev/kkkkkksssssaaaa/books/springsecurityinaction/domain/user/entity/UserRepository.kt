package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}