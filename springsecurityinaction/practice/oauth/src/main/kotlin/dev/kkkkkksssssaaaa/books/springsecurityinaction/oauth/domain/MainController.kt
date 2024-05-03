package dev.kkkkkksssssaaaa.books.springsecurityinaction.oauth.domain

import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @GetMapping
    fun main(
        token: OAuth2AuthenticationToken
    ): String {
        log.info(token.principal.toString())

        return "main.html"
    }
}