package dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.authentication.OtpAuthentication
import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.authentication.UsernamePasswordAuthentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.filter.OncePerRequestFilter

class InitialAuthenticationFilter(
    @Value("\${jwt.signing.key}")
    private val signingKey: String,
    private val manager: AuthenticationManager
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val username = request.getHeader("username")
        val password = request.getHeader("password")
        val code: String? = request.getHeader("code")

        if (code == null) {
            val auth = UsernamePasswordAuthentication(username, password)
            manager.authenticate(auth)
        } else {
            val auth = OtpAuthentication(username, code)
            val authenticate = manager.authenticate(auth)

            val jwt = JWT.create()
                .withClaim("username", username)
                .sign(Algorithm.HMAC256(signingKey))

            response.setHeader("Authorization", jwt)
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return !request.servletPath.equals("/login")
    }
}