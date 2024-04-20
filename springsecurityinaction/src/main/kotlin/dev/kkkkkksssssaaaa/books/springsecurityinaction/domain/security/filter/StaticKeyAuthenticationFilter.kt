package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class StaticKeyAuthenticationFilter(
    @Value("\${authorization.key}")
    private val authorizationKey: String
): Filter {
    override fun doFilter(
        req: ServletRequest,
        res: ServletResponse,
        filterChain: FilterChain
    ) {
        val servletRequest: HttpServletRequest = req as HttpServletRequest
        val servletResponse: HttpServletResponse = res as HttpServletResponse

        val authentication = servletRequest.getHeader("Authorization")

        if (authorizationKey == authentication) {
            filterChain.doFilter(req, res)
        } else {
            servletResponse.status = HttpServletResponse.SC_UNAUTHORIZED
        }
    }
}