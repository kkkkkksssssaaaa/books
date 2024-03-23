package dev.kkkkkksssssaaaa.books.springsecurityinaction.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class WebSecurityConfiguration {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic { config ->

        }.authorizeHttpRequests { config ->
            config.anyRequest().authenticated()
        }
        return http.build()
    }
}