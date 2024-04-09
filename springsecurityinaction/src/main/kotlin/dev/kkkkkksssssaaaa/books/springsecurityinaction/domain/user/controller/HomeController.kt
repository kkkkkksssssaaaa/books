package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    @GetMapping("/home")
    fun home(): String {
        return "home.html"
    }
}