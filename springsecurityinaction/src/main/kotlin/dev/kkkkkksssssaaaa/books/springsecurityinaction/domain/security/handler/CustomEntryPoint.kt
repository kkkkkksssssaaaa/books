package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

/**
 * 인증이 실패 했을 때의 응답 구성 설정
 * */
class CustomEntryPoint: AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.addHeader("message", "call entry point")
        response.sendError(HttpStatus.UNAUTHORIZED.value())
    }
}