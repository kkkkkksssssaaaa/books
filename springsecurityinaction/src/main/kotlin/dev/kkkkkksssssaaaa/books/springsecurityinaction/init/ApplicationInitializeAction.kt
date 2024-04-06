package dev.kkkkkksssssaaaa.books.springsecurityinaction.init

import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.Authorities
import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.AuthoritiesRepository
import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.User
import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ApplicationInitializeAction(
    private val userRepository: UserRepository,
    private val authoritiesRepository: AuthoritiesRepository,
    private val bcryptPasswordEncoder: PasswordEncoder
) {
    companion object {
        private const val USERNAME: String = "kkkkkksssssaaaa"
        private const val PASSWORD: String = "kkkkkksssssaaaa"
        private const val WRITE: String = "write"
    }

    @Bean
    fun doAction(): Boolean {
        userRepository.save(
            User(
                id = 1,
                username = USERNAME,
                password = "{bcrypt}${bcryptPasswordEncoder.encode(PASSWORD)}",
                enabled = true
            )
        )

        authoritiesRepository.save(
            Authorities(
                id = 1,
                username = USERNAME,
                authority = WRITE
            )
        )

        return true
    }
}