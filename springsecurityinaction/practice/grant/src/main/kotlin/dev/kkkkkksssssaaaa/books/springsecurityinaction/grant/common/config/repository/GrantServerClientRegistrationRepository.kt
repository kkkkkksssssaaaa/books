package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config.repository

import dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config.client.ClientProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType

@Configuration
class GrantServerClientRegistrationRepository(
    private val clientProperties: ClientProperties,
) {
    @Bean
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(clientRegistration())
    }

    @Bean
    fun clientRegistration(): ClientRegistration {
        return ClientRegistration.withRegistrationId(clientProperties.id)
            .clientId(clientProperties.clientId)
            .clientSecret(clientProperties.clientSecret)
            .clientName(clientProperties.clientId)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .scope("info")
            .authorizationUri("/auth/authorization")
            .tokenUri("/auth/token")
            .build()
    }
}