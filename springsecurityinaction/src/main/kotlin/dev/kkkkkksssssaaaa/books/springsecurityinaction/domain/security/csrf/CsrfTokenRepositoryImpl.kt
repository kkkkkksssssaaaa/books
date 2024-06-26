package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.csrf

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.csrf.entity.IssuedCsrfToken
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.DefaultCsrfToken
import org.springframework.transaction.annotation.Transactional
import java.util.*

class CsrfTokenRepositoryImpl(
    private val jpaRepository: CsrfTokenJpaRepository
) : CsrfTokenRepository {
    override fun generateToken(
        request: HttpServletRequest
    ): CsrfToken {
        return DefaultCsrfToken(
            "X-CSRF-TOKEN",
            "_csrf",
            UUID.randomUUID().toString()
        )
    }

    @Transactional
    override fun saveToken(
        csrfToken: CsrfToken,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val identifier: String = request.getHeader("X-IDENTIFIER")
        val findCsrfToken = jpaRepository.findTokenByIdentifier(identifier)

        if (findCsrfToken == null) {
            val entity = IssuedCsrfToken(
                identifier = identifier,
                token = csrfToken.token
            )

            jpaRepository.save(entity)
        } else {
            findCsrfToken.updateToken(csrfToken.token)
        }
    }

    override fun loadToken(
        request: HttpServletRequest
    ): CsrfToken? {
        val identifier: String = request.getHeader("X-IDENTIFIER")

        return jpaRepository.findTokenByIdentifier(identifier)?.let {
            DefaultCsrfToken(
                "X-CSRF-TOKEN",
                "_csrf",
                it.token
            )
        }
    }
}