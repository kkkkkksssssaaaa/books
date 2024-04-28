package dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.config

import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.provider.OtpAuthenticationProvider
import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.provider.UsernamePasswordAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity

@Configuration
class AuthenticationManagerConfiguration(
    private val usernamePasswordAuthenticationProvider: UsernamePasswordAuthenticationProvider,
    private val otpAuthenticationProvider: OtpAuthenticationProvider
) {
    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder::class.java)

        authenticationManagerBuilder.authenticationProvider(usernamePasswordAuthenticationProvider)
        authenticationManagerBuilder.authenticationProvider(otpAuthenticationProvider)

        return authenticationManagerBuilder.build()
    }
}