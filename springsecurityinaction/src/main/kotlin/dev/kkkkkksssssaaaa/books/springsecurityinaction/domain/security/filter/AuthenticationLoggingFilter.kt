package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory

class AuthenticationLoggingFilter: Filter {
    private val log = LoggerFactory.getLogger(AuthenticationLoggingFilter::class.java)!!

    override fun doFilter(
        req: ServletRequest,
        res: ServletResponse,
        filterChain: FilterChain
    ) {
        val servletRequest: HttpServletRequest = req as HttpServletRequest
        val requestId = servletRequest.requestId

        log.info("Successfully authenticated request with id {$requestId}")

        filterChain.doFilter(req, res)
    }
}