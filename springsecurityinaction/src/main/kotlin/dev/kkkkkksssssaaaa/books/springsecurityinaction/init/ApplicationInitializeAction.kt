package dev.kkkkkksssssaaaa.books.springsecurityinaction.init

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity.Authorities
import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity.AuthoritiesRepository
import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity.User
import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity.UserRepository
import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.password.PasswordEncoderConstants
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
        private const val READ: String = "read"
    }

    @Bean
    fun doAction(): Boolean {
        val encodedPassword: String =
            PasswordEncoderConstants.BCRYPT.wrappedKey() + bcryptPasswordEncoder.encode(PASSWORD)

        userRepository.save(
            User(
                id = 1,
                username = USERNAME,
                password = encodedPassword,
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

        authoritiesRepository.save(
            Authorities(
                id = 2,
                username = USERNAME,
                authority = READ
            )
        )

        return true
    }
}