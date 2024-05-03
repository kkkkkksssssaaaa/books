package dev.kkkkkksssssaaaa.books.springsecurityinaction.oauth

import dev.kkkkkksssssaaaa.books.springsecurityinaction.oauth.common.auth.client.GithubKeys
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
	value = [
		GithubKeys::class,
	],
)
class OauthApplication

fun main(args: Array<String>) {
	runApplication<OauthApplication>(*args)
}
