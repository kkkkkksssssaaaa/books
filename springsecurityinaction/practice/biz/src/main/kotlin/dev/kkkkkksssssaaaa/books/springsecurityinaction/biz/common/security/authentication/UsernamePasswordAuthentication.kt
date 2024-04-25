package dev.kkkkkksssssaaaa.books.springsecurityinaction.biz.common.security.authentication

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UsernamePasswordAuthentication: UsernamePasswordAuthenticationToken {
    constructor(
        principal: Any,
        credentials: Any
    ) : super(principal, credentials)

    constructor(
        principal: Any,
        credentials: Any,
        authorities: MutableCollection<out GrantedAuthority>
    ) : super(principal, credentials, authorities)
}