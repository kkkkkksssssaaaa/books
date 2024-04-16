package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.domain.Persistable

@Entity(name = "roles")
class Roles(
    username: String,
    role: String,
    id: Long? = null
): Persistable<Long> {
    @Id
    private var id: Long? = id

    @Column(name = "`username`")
    var username: String = username
        protected set

    @Column(name = "`role`")
    var role: String = role
        protected set

    override fun getId(): Long? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.id == null
    }
}