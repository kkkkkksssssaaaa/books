package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.provider

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

// 인증에 필요한 논리를 구현하는 계층
class CustomAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val username: String = authentication?.name ?: throw AuthenticationCredentialsNotFoundException("Not found username!")
        val rawPassword: String = authentication.credentials.toString()

        val user = userDetailsService.loadUserByUsername(username)

        return checkPassword(
            user = user,
            rawPassword = rawPassword
        )
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

    private fun checkPassword(
        user: UserDetails,
        rawPassword: String
    ): Authentication {
        if (passwordEncoder.matches(rawPassword, user.password)) {
            return UsernamePasswordAuthenticationToken(
                user.username,
                user.password,
                user.authorities
            )
        }

        throw BadCredentialsException("Invalid Authentication Entity")
    }
}