package dev.kkkkkksssssaaaa.books.springsecurityinaction.security.provider

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

// 인증에 필요한 논리를 구현하는 계층
@Configuration
class CustomAuthenticationProvider: AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val userName: String = authentication?.name ?: throw AuthenticationCredentialsNotFoundException("Not found username!")
        val password: String = authentication.credentials.toString()

        if ("kkkkkksssssaaaa" == userName && "kkkkkksssssaaaa" == password) {
            // 여기서 보통 UserDetailsService 와 PasswordEncoder 를 호출하여 사용자를 검증한다.
            return UsernamePasswordAuthenticationToken(
                userName,
                password,
                listOf()
            )
        } else {
            throw AuthenticationCredentialsNotFoundException("Error in authentication")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}