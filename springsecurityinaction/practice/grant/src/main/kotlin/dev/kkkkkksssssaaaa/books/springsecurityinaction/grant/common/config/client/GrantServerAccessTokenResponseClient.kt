package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

@Configuration
class GrantServerAccessTokenResponseClient {
    @Bean
    fun accessTokenResponseClient(): OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {
        val requestEntityConverter = OAuth2AuthorizationCodeGrantRequestEntityConverter()
        requestEntityConverter.addParametersConverter(parametersConverter())

        val accessTokenResponseClient = DefaultAuthorizationCodeTokenResponseClient()
        accessTokenResponseClient.setRequestEntityConverter(requestEntityConverter)

        return accessTokenResponseClient
    }

    private fun parametersConverter(): Converter<OAuth2AuthorizationCodeGrantRequest, MultiValueMap<String, String>> {
        return Converter<OAuth2AuthorizationCodeGrantRequest, MultiValueMap<String, String>> {
            LinkedMultiValueMap<String, String>().also { parameters ->
                parameters["audience"] = "grant-client"
            }
        }
    }
}