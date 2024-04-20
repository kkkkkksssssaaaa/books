package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.controller

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.service.CurrentUserService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val currentUser: CurrentUserService
) {
    companion object {
        private val log = LoggerFactory.getLogger(HelloController::class.java)!!
    }

    @GetMapping("/hello")
    fun hello(): String {
        return "${currentUser.name()}, Hello!"
    }

    @PostMapping("/hello")
    fun postHello(): String {
        return "${currentUser.name()}, Hello!"
    }

    @Async
    @GetMapping("/bye")
    fun bye() {
        log.info("bye bye, ${currentUser.name()}!!")
    }

    @GetMapping("/ciao")
    fun ciao(): String {
        return "Ciao, ${currentUser.ciao()}!"
    }

    @PostMapping("/ciao")
    fun postCiao(): String {
        return "Ciao, ${currentUser.ciao()}!"
    }

    @GetMapping("/hola")
    fun hola(): String {
        return "Hola, ${currentUser.hola()}!"
    }
}