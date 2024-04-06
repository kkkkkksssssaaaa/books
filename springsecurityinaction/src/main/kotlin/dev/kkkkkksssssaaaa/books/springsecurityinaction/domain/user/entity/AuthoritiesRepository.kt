package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity

import org.springframework.data.jpa.repository.JpaRepository

interface AuthoritiesRepository: JpaRepository<Authorities, Long> {
    fun findAllByUsername(username: String): List<Authorities>
    fun findAllByUsernameAndAuthorityIn(username: String, authority: List<String>): List<Authorities>
}