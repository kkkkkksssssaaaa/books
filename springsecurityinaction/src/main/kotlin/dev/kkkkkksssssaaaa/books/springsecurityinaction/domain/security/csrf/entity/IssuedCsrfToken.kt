package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.csrf.entity

import jakarta.persistence.*
import org.springframework.data.domain.Persistable

@Entity(name = "`issued_csrf_token`")
class IssuedCsrfToken(
    identifier: String,
    token: String,
    id: Long? = null
): Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = id

    @Column(name = "`identifier`")
    var identifier: String = identifier
        protected set

    @Column(name = "`token`")
    var token: String = token
        protected set

    fun updateToken(newToken: String) {
        this.token = newToken
    }

    override fun getId(): Long? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.id == null
    }
}