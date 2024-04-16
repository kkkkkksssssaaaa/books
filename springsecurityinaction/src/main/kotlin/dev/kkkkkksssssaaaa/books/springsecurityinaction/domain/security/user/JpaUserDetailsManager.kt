package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.security.user

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.entity.*
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JpaUserDetailsManager(
    private val users: UserRepository,
    private val authorities: AuthoritiesRepository,
    private val roles: RolesRepository
): UserDetailsManager {
    companion object {
        private val log = LoggerFactory.getLogger(JpaUserDetailsManager::class.java)!!
    }

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails {
        log.info("find user details, username=$username")

        return users.findByUsername(username)?.let { user ->
            val authorities: List<String> =
                authorities.findAllByUsername(username)
                    .map { it.authority }
            val roles: List<String> =
                roles.findAllByUsername(username)
                    .map { it.role }

            SecurityUser(
                username = user.username,
                password = user.password,
                authorities = authorities,
                roles = roles
            )
        } ?: throw UsernameNotFoundException("$username is not found!")
    }

    override fun createUser(userDetails: UserDetails) {
        val securityUser = userDetails as SecurityUser

        users.save(
            User(
                username = securityUser.username,
                password = securityUser.password,
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

        securityUser.roles.forEach {
            roles.save(
                Roles(
                    username = securityUser.username,
                    role = it
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
        TODO("This feature was not implemented")
    }

    override fun userExists(username: String): Boolean {
        return users.findByUsername(username)?.let {
            true
        } ?: false
    }
}