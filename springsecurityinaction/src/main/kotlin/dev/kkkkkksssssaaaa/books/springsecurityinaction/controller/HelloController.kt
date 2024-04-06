package dev.kkkkkksssssaaaa.books.springsecurityinaction.controller

import dev.kkkkkksssssaaaa.books.springsecurityinaction.service.CurrentUserService
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