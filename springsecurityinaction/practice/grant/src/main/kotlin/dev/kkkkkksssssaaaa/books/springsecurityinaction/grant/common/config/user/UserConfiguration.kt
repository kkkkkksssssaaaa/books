package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config.user

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class UserConfiguration {
    @Bean
    fun userDetailService(): UserDetailsService {
        val manager = InMemoryUserDetailsManager()
        val user = User.withUsername("kkkkkksssssaaaa")
            .password("1234")
            .authorities("read")
            .build()

        manager.createUser(user)

        return manager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}