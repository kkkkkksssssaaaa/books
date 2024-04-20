package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.controller

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainPageController(
    private val productService: ProductService
) {
    private val log = LoggerFactory.getLogger(MainPageController::class.java)!!

    @GetMapping("/main")
    fun main(
        authentication: Authentication,
        model: Model
    ): String {
        model.addAttribute("username", authentication.name)
        model.addAttribute("products", productService.findAll())

        return "main"
    }

    @PostMapping("/product")
    fun addProduct(@RequestParam name: String): String {
        log.info("Adding product $name")

        return "main"
    }
}