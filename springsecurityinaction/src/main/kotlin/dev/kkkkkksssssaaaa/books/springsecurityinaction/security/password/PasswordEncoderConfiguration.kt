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
    fun delegatingPasswordEncoder(): PasswordEncoder {
        val encoders: Map<String, PasswordEncoder> =
            mapOf(
                "noop" to noOpsPasswordEncoder(),
                "bcrypt" to bcryptPasswordEncoder(),
                "scrypt" to scryptPasswordEncoder()
            )

        return DelegatingPasswordEncoder("bcrypt", encoders)
    }

    @Bean
    fun bcryptPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun scryptPasswordEncoder(): PasswordEncoder {
        return SCryptPasswordEncoder(16384, 8, 1, 32, 64)
    }

    @Bean
    fun noOpsPasswordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}