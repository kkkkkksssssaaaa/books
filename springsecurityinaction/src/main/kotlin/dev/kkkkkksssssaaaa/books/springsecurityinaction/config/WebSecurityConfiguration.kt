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
        http.httpBasic { config ->
            config.realmName("OTHER")
                .authenticationEntryPoint(CustomEntryPoint())
        }.authorizeHttpRequests { config ->
            config.anyRequest().authenticated()
        }.authenticationProvider(authenticationProvider)
        return http.build()
    }
}