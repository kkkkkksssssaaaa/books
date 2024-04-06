package dev.kkkkkksssssaaaa.books.springsecurityinaction.security.provider

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

// 인증에 필요한 논리를 구현하는 계층
@Configuration
class CustomAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val delegatingPasswordEncoder: PasswordEncoder,
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val username: String = authentication?.name ?: throw AuthenticationCredentialsNotFoundException("Not found username!")
        val password: String = authentication.credentials.toString()

        val user = userDetailsService.loadUserByUsername(username)

        if (delegatingPasswordEncoder.matches(password, user.password)) {
            return UsernamePasswordAuthenticationToken(
                username,
                password,
                user.authorities
            )
        }

        throw BadCredentialsException("Invalid Authentication Entity")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}