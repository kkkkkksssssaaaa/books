package dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.auth

import jakarta.persistence.*
import org.springframework.data.domain.Persistable

@Entity(name = "`otp`")
class Otp(
    username: String,
    code: String,
    id: Long? = null,
): Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = id

    @Column(name = "`username`")
    var username: String = username
        protected set

    @Column(name = "`code`")
    var code: String = code
        protected set

    fun updateCode(code: String) {
        this.code = code
    }

    override fun getId(): Long? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.id == null
    }
}