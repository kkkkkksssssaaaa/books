package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config.repository

import dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config.client.ClientProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import java.time.LocalDateTime
import java.time.ZoneOffset

@Configuration
class GrantServerAuthorizationRequestRepository(
    private val clientProperties: ClientProperties,
    private val clientRegistrationRepository: ClientRegistrationRepository
) {
    @Bean
    fun authorizationRequestRepository(): AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
        val now = LocalDateTime.now()
        val expiredAt = now.plusYears(1L)

        val oAuth2AccessToken = OAuth2AccessToken(
            OAuth2AccessToken.TokenType.BEARER,
            "issued-by-grant-server",
            now.toInstant(ZoneOffset.UTC),
            expiredAt.toInstant(ZoneOffset.UTC)
        )

        val client = OAuth2AuthorizedClient(
            clientRegistrationRepository.findByRegistrationId(clientProperties.id),
            "default-principle",
            oAuth2AccessToken
        )

        return HttpSessionOAuth2AuthorizationRequestRepository()
    }
}