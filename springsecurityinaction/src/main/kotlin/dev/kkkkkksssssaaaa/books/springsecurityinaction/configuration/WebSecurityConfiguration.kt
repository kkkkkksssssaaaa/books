package dev.kkkkkksssssaaaa.books.springsecurityinaction.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class WebSecurityConfiguration {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic { config ->

        }.authorizeHttpRequests { config ->
            config.anyRequest().authenticated()
        }
        return http.build()
    }
}