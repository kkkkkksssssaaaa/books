package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.web.csrf.CsrfToken

class CsrfTokenLogger: Filter {
    private val log = LoggerFactory.getLogger(CsrfTokenLogger::class.java)!!

    override fun doFilter(
        req: ServletRequest,
        res: ServletResponse,
        filterChain: FilterChain
    ) {
        val tokenObject: Any = req.getAttribute("_csrf")
        val token: CsrfToken = tokenObject as CsrfToken

        log.info("CSRF token ${token.token}")

        filterChain.doFilter(req, res)
    }
}