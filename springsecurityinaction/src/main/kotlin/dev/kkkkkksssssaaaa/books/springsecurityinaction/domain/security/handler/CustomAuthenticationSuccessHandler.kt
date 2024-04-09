package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationSuccessHandler: AuthenticationSuccessHandler {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        authentication.authorities
            .first { authorityObject ->
                authorityObject.authority == "read"
            }?.let {
                log.info("authentication success!")
                response.sendRedirect("/home")
            } ?: {
                log.info("authentication failed! redirect to error page.")
                response.sendRedirect("/error")
            }
    }
}