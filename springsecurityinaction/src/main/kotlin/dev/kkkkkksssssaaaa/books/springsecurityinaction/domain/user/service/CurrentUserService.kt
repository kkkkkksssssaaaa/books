package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class CurrentUserService {
    fun name(): String {
        val authentication = SecurityContextHolder.getContext().authentication

        return authentication.name
    }
}