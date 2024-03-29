package dev.kkkkkksssssaaaa.books.springsecurityinaction.security.user

import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.Authorities
import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.AuthoritiesRepository
import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.User
import dev.kkkkkksssssaaaa.books.springsecurityinaction.entity.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JpaUserDetailsManager(
    private val users: UserRepository,
    private val authorities: AuthoritiesRepository
): UserDetailsManager {
    companion object {
        private val log = LoggerFactory.getLogger(JpaUserDetailsManager::class.java)!!
    }

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails {
        log.info("하이헬로안녕")
        return users.findByUsername(username)?.let {
            val authority: String =
                authorities.findAllByUsername(username)
                    .first()
                    .toString()

            SecurityUser(
                username = it.username,
                password = it.password,
                authority = authority
            )
        } ?: throw UsernameNotFoundException("$username is not found!")
    }

    override fun createUser(securityUser: UserDetails) {
        users.save(
            User(
                username = securityUser.username,
                password = securityUser.password,
                enabled = true
            )
        )

        securityUser.authorities.forEach {
            authorities.save(
                Authorities(
                    username = securityUser.username,
                    authority = it.toString()
                )
            )
        }
    }

    override fun updateUser(securityUser: UserDetails) {
        users.findByUsername(securityUser.username)?.let {
            users.save(
                User(
                    id = it.id,
                    username = securityUser.username,
                    password = securityUser.password
                )
            )
        }
    }

    override fun deleteUser(username: String) {
        users.delete(
            users.findByUsername(username) ?: throw IllegalArgumentException()
        )
    }

    override fun changePassword(oldPassword: String, newPassword: String) {
        TODO("Not yet implemented")
    }

    override fun userExists(username: String): Boolean {
        TODO("Not yet implemented")
    }
}