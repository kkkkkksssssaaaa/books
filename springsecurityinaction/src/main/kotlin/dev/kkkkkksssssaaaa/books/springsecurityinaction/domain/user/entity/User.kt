package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.password.PasswordEncoderConstants
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.domain.Persistable

@Entity(name = "`user`")
class User(
    username: String,
    password: String,
    id: Long? = null,
    algorithm: String = PasswordEncoderConstants.BCRYPT.key
): Persistable<Long> {
    @Id
    private var id: Long? = id

    @Column(name = "`username`")
    var username: String = username
        protected set

    @Column(name = "`password`")
    var password: String = password
        protected set

    @Column(name = "`algorithm`")
    var algorithm: String = algorithm
        protected set

    override fun getId(): Long? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.id == null
    }
}