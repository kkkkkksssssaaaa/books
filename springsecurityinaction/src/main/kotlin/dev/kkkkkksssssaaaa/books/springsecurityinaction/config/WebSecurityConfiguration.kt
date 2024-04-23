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
import org.springframework.web.cors.CorsConfiguration

@Configuration
class WebSecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider,
    private val successHandler: AuthenticationSuccessHandler,
    private val failureHandler: AuthenticationFailureHandler,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.formLogin {
            it.permitAll()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
        }.cors {
            it.configurationSource {
                val corsConfig = CorsConfiguration()

                corsConfig.allowedOriginPatterns = listOf(
                    "example.com",
                    "example.org"
                )

                corsConfig.allowedMethods = listOf(
                    "GET",
                    "POST",
                    "PUT",
                    "DELETE"
                )

                corsConfig
            }
        }.httpBasic {
            it.authenticationEntryPoint(CustomEntryPoint())
        }.authorizeHttpRequests {
            it.anyRequest().permitAll()
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