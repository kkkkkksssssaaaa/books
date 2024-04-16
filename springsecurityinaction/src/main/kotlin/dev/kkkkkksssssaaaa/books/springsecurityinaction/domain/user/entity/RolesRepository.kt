package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity

import org.springframework.data.jpa.repository.JpaRepository

interface RolesRepository: JpaRepository<Roles, Long> {
    fun findAllByUsername(username: String): List<Roles>
}