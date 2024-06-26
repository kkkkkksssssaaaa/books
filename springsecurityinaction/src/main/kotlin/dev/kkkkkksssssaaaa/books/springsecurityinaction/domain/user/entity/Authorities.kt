package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity

import jakarta.persistence.*
import org.springframework.data.domain.Persistable

@Entity(name = "authorities")
class Authorities(
    username: String,
    authority: String,
    id: Long? = null,
): Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = id

    @Column(name = "`username`")
    var username: String = username
        protected set

    @Column(name = "`authority`")
    var authority: String = authority
        protected set

    override fun getId(): Long? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.id == null
    }
}