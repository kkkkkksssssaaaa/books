package dev.kkkkkksssssaaaa.books.springsecurityinaction.security.user

import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(
    private val user: User
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(
            SimpleGrantedAuthority(user.authority)
        )
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}