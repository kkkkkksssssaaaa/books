package dev.kkkkkksssssaaaa.books.springsecurityinaction.oauth.common.auth.repository

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository

@Configuration
class GithubRegistrationRepository(
    private val clientRegistration: ClientRegistration
) {
    @Bean
    fun registrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(clientRegistration)
    }
}