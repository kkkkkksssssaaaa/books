package dev.kkkkkksssssaaaa.books.springsecurityinaction.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "`user`")
class User(
    id: Long?,
    username: String,
    password: String,
    authority: String
) {
    @Id
    var id: Long? = id
        protected set

    @Column(name = "`username`")
    var username: String = username
        protected set

    @Column(name = "`password`")
    var password: String = password
        protected set

    @Column(name = "`authority`")
    var authority: String = authority
        protected set
}