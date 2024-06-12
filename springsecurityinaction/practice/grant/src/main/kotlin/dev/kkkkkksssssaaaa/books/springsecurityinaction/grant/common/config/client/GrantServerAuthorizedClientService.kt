package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository

@Configuration
class GrantServerAuthorizedClientService(
    private val clientRegistrationRepository: ClientRegistrationRepository
) {
    @Bean
    fun authorizedClientService(): OAuth2AuthorizedClientService {
        return InMemoryOAuth2AuthorizedClientService(
            clientRegistrationRepository
        )
    }
}