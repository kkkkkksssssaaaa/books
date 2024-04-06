package dev.kkkkkksssssaaaa.books.springsecurityinaction

import org.junit.jupiter.api.Test
import java.security.SecureRandom

class SpringSecurityInActionApplicationTests {
    @Test
    fun createSecureRandom() {
        println("secureRandom=${SecureRandom.getInstanceStrong()}")
        Thread.sleep(5000)
    }
}
