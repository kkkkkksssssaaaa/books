package dev.kkkkkksssssaaaa.books.springsecurityinaction.config

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.handler.CustomEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class WebSecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider,
    private val successHandler: AuthenticationSuccessHandler,
    private val failureHandler: AuthenticationFailureHandler
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
            it.requestMatchers("/hello").hasRole("ADMIN")
                .requestMatchers("/ciao").hasRole("MANAGER")
        }.authenticationProvider(authenticationProvider)
        .build()
    }
}