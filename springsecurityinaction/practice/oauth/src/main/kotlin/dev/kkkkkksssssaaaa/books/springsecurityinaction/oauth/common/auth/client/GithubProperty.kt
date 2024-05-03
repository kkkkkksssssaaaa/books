package dev.kkkkkksssssaaaa.books.springsecurityinaction.oauth.common.auth.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
data class GithubProperty(
    @Value("\${auth.client.github.id}") val id: String,
    @Value("\${auth.client.github.name}") val name: String,
    val keys: GithubKeys,
)

@ConfigurationProperties(prefix = "auth.client.github.keys")
data class GithubKeys(
    val id: String,
    val secret: String
)
