package dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.auth

import dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.user.User
import dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.user.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val userService: UserService
) {
    @PostMapping("/user")
    fun addUser(
        @RequestBody user: User
    ) {
        userService.addUser(user)
    }

    @PostMapping("/user/auth")
    fun auth(
        @RequestBody user: User
    ) {
        userService.auth(user)
    }

    @PostMapping("/otp/check")
    fun check(
        @RequestBody otp: Otp,
        servletResponse: HttpServletResponse
    ) {
        if (userService.check(otp)) {
            servletResponse.status = HttpServletResponse.SC_OK
        } else {
            servletResponse.status = HttpServletResponse.SC_FORBIDDEN
        }
    }
}