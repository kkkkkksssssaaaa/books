package dev.kkkkkksssssaaaa.books.springsecurityinaction.security.user

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class SecurityUserConfiguration {
    @Bean
    fun jpaDaoAuthenticationProvider(
        jpaUserDetailsManager: JpaUserDetailsManager
    ): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()

        daoAuthenticationProvider.setUserDetailsService(jpaUserDetailsManager)
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())

        return daoAuthenticationProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}