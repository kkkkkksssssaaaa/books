package dev.kkkkkksssssaaaa.books.springsecurityinaction.security

import dev.kkkkkksssssaaaa.books.springsecurityinaction.security.provider.CustomAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class WebSecurityConfiguration(
    private val customAuthenticationProvider: CustomAuthenticationProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic { config ->

        }.authorizeHttpRequests { config ->
            config.anyRequest().authenticated()
        }.authenticationProvider(customAuthenticationProvider)
        return http.build()
    }
}