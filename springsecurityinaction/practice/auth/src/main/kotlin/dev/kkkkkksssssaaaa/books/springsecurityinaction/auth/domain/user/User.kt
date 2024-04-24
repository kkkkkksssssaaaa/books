package dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.user

import jakarta.persistence.*
import org.springframework.data.domain.Persistable

@Entity(name = "`user`")
class User(
    username: String,
    password: String,
    id: Long? = null,
): Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = id

    @Column(name = "`username`")
    var username: String = username
        protected set

    @Column(name = "`password`")
    var password: String = password
        protected set

    fun encryptPassword(encodedPassword: String) {
        this.password = encodedPassword
    }

    override fun getId(): Long? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.id == null
    }
}