package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.filter.OncePerRequestFilter

class AuthenticationLoggingFilter: OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(AuthenticationLoggingFilter::class.java)!!

    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestId = req.requestId

        log.info("Successfully authenticated request with id {$requestId}")

        filterChain.doFilter(req, res)
    }
}