package dev.kkkkkksssssaaaa.books.springsecurityinaction.oauth.common.auth.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.registration.ClientRegistration

@Configuration
class GithubClientRegistration(
    private val property: GithubProperty
) {
    @Bean
    fun clientRegistration(): ClientRegistration {
        return CommonOAuth2Provider.GITHUB
                .getBuilder(property.id)
                .clientId(property.keys.id)
                .clientSecret(property.keys.secret)
                .build()
    }
}