package dev.kkkkkksssssaaaa.books.springsecurityinaction.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class WebSecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.formLogin {
            it.permitAll()
                .defaultSuccessUrl("/home", true)
        }.authorizeHttpRequests {
            it.anyRequest()
                .authenticated()
        }.authenticationProvider(authenticationProvider)
        .build()
    }
}