package dev.kkkkkksssssaaaa.books.springsecurityinaction.security.user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class InMemoryUserDetailsService(
    private val users: List<UserDetails>
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return try {
            return users.first { u -> u.username == username }
        } catch (ex: NoSuchElementException) {
            throw UsernameNotFoundException("$username is not found!")
        }
    }
}