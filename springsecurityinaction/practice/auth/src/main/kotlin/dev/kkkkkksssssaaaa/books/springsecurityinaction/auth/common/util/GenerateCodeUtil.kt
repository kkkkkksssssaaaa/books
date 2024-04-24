package dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.common.util

import java.security.SecureRandom

class GenerateCodeUtil {
    companion object {
        fun generateCode(): String {
            val random: SecureRandom = SecureRandom.getInstanceStrong()
            val code = random.nextInt(9000) + 1000

            return code.toString()
        }
    }
}