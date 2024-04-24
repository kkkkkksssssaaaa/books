package dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.auth

import org.springframework.data.jpa.repository.JpaRepository

interface OtpRepository: JpaRepository<Otp, Long> {
    fun findByUsername(username: String): Otp?
}