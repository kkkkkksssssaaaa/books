package dev.kkkkkksssssaaaa.books.springsecurityinaction.oauth.common

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.web.SecurityFilterChain

@Configuration
class WebSecurityConfiguration(
    private val registrationRepository: ClientRegistrationRepository
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .oauth2Login{
                it.clientRegistrationRepository(registrationRepository)
            }
            .authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
            .build()
    }
}