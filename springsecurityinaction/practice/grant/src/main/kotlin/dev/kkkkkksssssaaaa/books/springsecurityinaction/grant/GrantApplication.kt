package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class GrantApplication

fun main(args: Array<String>) {
	runApplication<GrantApplication>(*args)
}
