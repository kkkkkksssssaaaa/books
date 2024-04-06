package dev.kkkkkksssssaaaa.books.springsecurityinaction.security.password

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

@Configuration
class PasswordEncoderConfiguration {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        val encoders: Map<String, PasswordEncoder> =
            mapOf(
                "noop" to NoOpPasswordEncoder.getInstance(),
                "bycrypt" to BCryptPasswordEncoder(),
                "scrypt" to SCryptPasswordEncoder(16384, 8, 1, 32, 64)
            )

        return DelegatingPasswordEncoder("bcrypt", encoders)
    }
}