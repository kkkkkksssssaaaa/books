package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.csrf

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.csrf.entity.IssuedCsrfToken
import org.springframework.data.jpa.repository.JpaRepository

interface CsrfTokenJpaRepository: JpaRepository<IssuedCsrfToken, Long> {
    fun findTokenByIdentifier(identifier: String): IssuedCsrfToken?
}