package dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.proxy

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity

@Component
class AuthenticationServerProxy(
    @Value("\${auth.server.base.url}")
    private val authServerUrl: String
) {
    private val restClient: RestClient = RestClient.create()

    fun sendAuth(
        username: String,
        password: String
    ) {
        val url = "${authServerUrl}/user/auth"

        restClient.post()
            .uri(url)
            .body {
                mapOf(
                    "username" to username,
                    "password" to password
                )
            }
            .retrieve()
    }

    fun sendOtp(
        username: String,
        code: String
    ): Boolean {
        val url = "${authServerUrl}/otp/check"

        val response = restClient.post()
            .uri(url)
            .body {
                mapOf(
                    "username" to username,
                    "code" to code
                )
            }
            .retrieve()
            .toEntity<Any>()

        return response.statusCode == HttpStatus.OK
    }
}