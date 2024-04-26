package dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.config

import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.filter.InitialAuthenticationFilter
import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.filter.JwtAuthenticationFilter
import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.provider.OtpAuthenticationProvider
import dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.provider.UsernamePasswordAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class WebSecurityConfiguration(
    private val initialAuthenticationFilter: InitialAuthenticationFilter,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val otpAuthenticationProvider: OtpAuthenticationProvider,
    private val usernamePasswordAuthenticationProvider: UsernamePasswordAuthenticationProvider,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf { it.disable() }
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .authenticationProvider(otpAuthenticationProvider)
            .authenticationProvider(usernamePasswordAuthenticationProvider)
            .addFilterAfter(
                initialAuthenticationFilter,
                BasicAuthenticationFilter::class.java
            )
            .addFilterAfter(
                jwtAuthenticationFilter,
                BasicAuthenticationFilter::class.java
            )
            .build()
    }
}