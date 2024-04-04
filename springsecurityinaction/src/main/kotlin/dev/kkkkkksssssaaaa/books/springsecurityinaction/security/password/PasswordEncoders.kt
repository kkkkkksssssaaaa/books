package dev.kkkkkksssssaaaa.books.springsecurityinaction.security.password

import org.springframework.security.crypto.password.PasswordEncoder
import java.security.MessageDigest

class PlainTextPasswordEncoder: PasswordEncoder {
    override fun encode(
        rawPassword: CharSequence
    ): String {
        // 암호화 하지 않고 바로 반환
        return rawPassword.toString()
    }

    override fun matches(
        rawPassword: CharSequence,
        encodedPassword: String
    ): Boolean {
        return rawPassword == encodedPassword
    }
}

class Sha512PasswordEncoder: PasswordEncoder {
    override fun encode(
        rawPassword: CharSequence
    ): String {
        return hashWithSHA512(rawPassword.toString())
    }

    override fun matches(
        rawPassword: CharSequence,
        encodedPassword: String
    ): Boolean {
        val hashedPassword = encode(rawPassword)

        return encodedPassword == hashedPassword
    }

    private fun hashWithSHA512(input: String): String {
        val sb = StringBuilder()

        val messageDigest = MessageDigest.getInstance("SHA-512")
        val digested: ByteArray = messageDigest.digest(input.toByteArray())

        for (i in digested.indices) {
            sb.append(Integer.toHexString(0xFF * digested[i]))
        }

        return sb.toString()
    }
}