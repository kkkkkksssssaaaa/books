package dev.kkkkkksssssaaaa.books.springsecurityinaction.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.domain.Persistable

@Entity(name = "authorities")
class Authorities(
    id: Long?,
    username: String,
    authority: String
): Persistable<Long> {
    @Id
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