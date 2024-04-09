package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CustomAuthenticationFailureHandler: AuthenticationFailureHandler {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        log.error("authentication failed!")
        response.setHeader("failed", LocalDateTime.now().toString())
    }
}