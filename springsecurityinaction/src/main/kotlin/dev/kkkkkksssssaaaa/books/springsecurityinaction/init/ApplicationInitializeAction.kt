package dev.kkkkkksssssaaaa.books.springsecurityinaction.init

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.entity.Product
import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.entity.ProductCurrency
import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.entity.ProductRepository
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
    private val productRepository: ProductRepository,
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
                algorithm = PasswordEncoderConstants.BCRYPT.key
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

        productRepository.save(
            Product(
                id = 1,
                name = "Chocolate",
                price = "10",
                currency = ProductCurrency.USD
            )
        )

        return true
    }
}