package dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.authentication.UsernamePasswordAuthentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    @Value("\${jwt.signing.key}") private val signingKey: String,
): OncePerRequestFilter() {
    private val verifier = JWT.require(Algorithm.HMAC256(signingKey)).build()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getHeader("Authorization")
        val decodedJwt = verifier.verify(JWT.decode(jwt))

        val extractedUsername = decodedJwt.getClaim("username")
        val authority = SimpleGrantedAuthority("user")

        val auth = UsernamePasswordAuthentication(
            extractedUsername,
            null,
            mutableListOf(authority)
        )

        SecurityContextHolder.getContext().authentication = auth
        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath.equals("/login")
    }
}