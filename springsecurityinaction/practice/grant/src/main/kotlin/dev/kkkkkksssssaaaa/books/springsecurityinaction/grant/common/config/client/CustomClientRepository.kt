package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings

@Configuration
class CustomClientRepository(
    private val properties: ClientProperties
) {
    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        return InMemoryRegisteredClientRepository(client())
    }

    @Bean
    fun client(): RegisteredClient {
        return RegisteredClient.withId(properties.id)
            .clientId(properties.clientId)
            .clientSecret(properties.clientSecret)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.PASSWORD)
            .redirectUri(properties.redirectUris[0])
            .redirectUri(properties.redirectUris[1])
            .scope("read")
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
            .build()
    }
}