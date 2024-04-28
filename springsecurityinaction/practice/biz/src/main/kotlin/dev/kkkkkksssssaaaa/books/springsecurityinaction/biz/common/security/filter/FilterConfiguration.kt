package dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.filter

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager


@Configuration
class FilterConfiguration {
    @Bean
    fun initialAuthenticationFilter(
        @Value("\${jwt.signing.key}") signingKey: String,
        authenticationManager: AuthenticationManager
    ): InitialAuthenticationFilter {
        return InitialAuthenticationFilter(signingKey, authenticationManager)
    }
}