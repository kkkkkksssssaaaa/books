package dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.provider

import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.proxy.AuthenticationServerProxy
import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.authentication.OtpAuthentication
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class OtpAuthenticationProvider(
    private val proxy: AuthenticationServerProxy
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val otp = authentication.credentials.toString()

        val response = proxy.sendOtp(
            username = username,
            code = otp
        )

        if (response) {
            return OtpAuthentication(username, otp)
        } else {
            throw BadCredentialsException("Bad credentials.")
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return OtpAuthentication::class.java.isAssignableFrom(authentication)
    }
}