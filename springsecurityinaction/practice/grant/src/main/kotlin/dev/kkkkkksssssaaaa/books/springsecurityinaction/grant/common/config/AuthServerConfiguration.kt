package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.client.*
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.*
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class AuthServerConfiguration(
    private val clientRegistrationRepository: ClientRegistrationRepository,
    private val authorizedClientRepository: OAuth2AuthorizedClientRepository,
    private val authorizedClientService: OAuth2AuthorizedClientService,
    private val authorizationRequestRepository: AuthorizationRequestRepository<OAuth2AuthorizationRequest>,
    private val accessTokenResponseClient: OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest>
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.oauth2Client { client ->
            client.clientRegistrationRepository(clientRegistrationRepository)
            client.authorizedClientRepository(authorizedClientRepository)
            client.authorizedClientService(authorizedClientService)
            client.authorizationCodeGrant { grant ->
                    grant.authorizationRequestRepository(authorizationRequestRepository)
                    grant.authorizationRequestResolver(authorizationRequestResolver())
                    grant.accessTokenResponseClient(accessTokenResponseClient)
                }
            }

        return http.build()
    }

    @Bean
    fun authorizedClientManager(
        clientRegistrationRepository: ClientRegistrationRepository,
        authorizedClientRepository: OAuth2AuthorizedClientRepository
    ): OAuth2AuthorizedClientManager {
        val authorizedClientProvider: OAuth2AuthorizedClientProvider =
            OAuth2AuthorizedClientProviderBuilder.builder()
                .authorizationCode()
                .refreshToken()
                .clientCredentials()
                .password()
                .build()
        val authorizedClientManager = DefaultOAuth2AuthorizedClientManager(
            clientRegistrationRepository,
            authorizedClientRepository
        )

        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider)

        return authorizedClientManager
    }

    private fun authorizationRequestResolver(): OAuth2AuthorizationRequestResolver {
        return DefaultOAuth2AuthorizationRequestResolver(
            clientRegistrationRepository, "/auth/login/{clientId}"
        )
    }
}