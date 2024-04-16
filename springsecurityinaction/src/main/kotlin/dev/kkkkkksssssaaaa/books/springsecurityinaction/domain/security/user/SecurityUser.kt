package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(
    private val username: String,
    private val password: String,
    private val authorities: List<String>,
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.authorities.map { authority ->
            SimpleGrantedAuthority(authority)
        }.toMutableList()
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun getPassword(): String {
        return this.password
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