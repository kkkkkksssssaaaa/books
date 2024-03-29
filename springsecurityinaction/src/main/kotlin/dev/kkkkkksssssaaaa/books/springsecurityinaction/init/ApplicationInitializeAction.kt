package dev.kkkkkksssssaaaa.books.springsecurityinaction.init

import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.Authorities
import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.AuthoritiesRepository
import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.User
import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationInitializeAction(
    private val userRepository: UserRepository,
    private val authoritiesRepository: AuthoritiesRepository
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
                password = PASSWORD,
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