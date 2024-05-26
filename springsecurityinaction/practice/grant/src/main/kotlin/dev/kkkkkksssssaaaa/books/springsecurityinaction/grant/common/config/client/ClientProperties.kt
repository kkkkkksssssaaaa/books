package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config.client

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "custom.security.client")
data class ClientProperties(
    val id: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUris: List<String>
)