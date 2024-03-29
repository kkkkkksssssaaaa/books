package dev.kkkkkksssssaaaa.books.springsecurityinaction.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.domain.Persistable

@Entity(name = "`user`")
class User(
    id: Long?,
    username: String,
    password: String,
    enabled: Boolean = true
): Persistable<Long> {
    @Id
    private var id: Long? = id

    @Column(name = "`username`")
    var username: String = username
        protected set

    @Column(name = "`password`")
    var password: String = password
        protected set

    @Column(name = "`enabled`")
    var enabled: Boolean = enabled
        protected set

    override fun getId(): Long? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.id == null
    }
}