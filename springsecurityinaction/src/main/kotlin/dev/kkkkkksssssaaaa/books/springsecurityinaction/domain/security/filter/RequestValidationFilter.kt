package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class RequestValidationFilter: Filter {
    override fun doFilter(
        req: ServletRequest,
        res: ServletResponse,
        filterChain: FilterChain
    ) {
        val servletRequest: HttpServletRequest = req as HttpServletRequest
        val servletResponse: HttpServletResponse = res as HttpServletResponse

        val requestId = servletRequest.requestId!!

        if (requestId.isBlank()) {
            servletResponse.status = HttpServletResponse.SC_BAD_REQUEST
            return
        }

        filterChain.doFilter(req, res)
    }
}