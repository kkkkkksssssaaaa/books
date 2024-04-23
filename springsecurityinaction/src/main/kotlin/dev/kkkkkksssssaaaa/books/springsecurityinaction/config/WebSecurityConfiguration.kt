package dev.kkkkkksssssaaaa.books.springsecurityinaction.config

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.filter.AuthenticationLoggingFilter
import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.filter.CsrfTokenLogger
import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.handler.CustomEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.security.web.csrf.CsrfTokenRepository

@Configuration
class WebSecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider,
    private val successHandler: AuthenticationSuccessHandler,
    private val failureHandler: AuthenticationFailureHandler,
    private val csrfTokenRepository: CsrfTokenRepository
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.formLogin {
            it.permitAll()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
        }.httpBasic {
            it.authenticationEntryPoint(CustomEntryPoint())
        }.authorizeHttpRequests {
            it.anyRequest().permitAll()
        }.csrf {
            it.csrfTokenRepository(csrfTokenRepository)
            it.ignoringRequestMatchers("/ciao")
        }.addFilterAfter(
            CsrfTokenLogger(),
            CsrfFilter::class.java
        ).addFilterAfter(
            AuthenticationLoggingFilter(),
            BasicAuthenticationFilter::class.java
        ).authenticationProvider(authenticationProvider)
        .build()
    }
}