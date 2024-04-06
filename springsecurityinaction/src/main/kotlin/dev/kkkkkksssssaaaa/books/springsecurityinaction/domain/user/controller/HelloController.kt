package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.controller

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.service.CurrentUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val currentUser: CurrentUserService
) {
    @GetMapping("/hello")
    fun hello(): String {
        return "${currentUser.name()}, Hello!"
    }
}