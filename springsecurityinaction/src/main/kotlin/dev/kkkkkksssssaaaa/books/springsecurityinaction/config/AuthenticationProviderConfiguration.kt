package dev.kkkkkksssssaaaa.books.springsecurityinaction.config

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.provider.CustomAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthenticationProviderConfiguration {
    @Bean
    fun authenticationProvider(
        userDetailsService: UserDetailsService,
        delegatingPasswordEncoder: PasswordEncoder,
    ): AuthenticationProvider {
        return CustomAuthenticationProvider(
            userDetailsService = userDetailsService,
            passwordEncoder = delegatingPasswordEncoder
        )
    }
}