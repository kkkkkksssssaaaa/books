package dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.user

import dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.common.util.GenerateCodeUtil
import dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.auth.Otp
import dev.kkkkkksssssaaaa.books.springsecurityinaction.auth.domain.auth.OtpRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val otpRepository: OtpRepository
) {
    @Transactional
    fun addUser(
        user: User
    ) {
        user.encryptPassword(
            passwordEncoder.encode(user.password)
        )

        userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun auth(user: User) {
        val findUser: User = userRepository.findByUsername(user.username)
            ?: throw BadCredentialsException("Bad Credentials.")

        if (passwordEncoder.matches(user.password, findUser.password)) {
            renewOtp(findUser)
        } else {
            throw BadCredentialsException("Bad Credentials.")
        }
    }

    @Transactional(readOnly = true)
    fun check(otpToValidate: Otp): Boolean {
        val findOtp = otpRepository.findByUsername(otpToValidate.username) ?: return false

        return otpToValidate.code == findOtp.code
    }

    private fun renewOtp(user: User) {
        val code = GenerateCodeUtil.generateCode()
        val userOtp = otpRepository.findByUsername(user.username)

        if (userOtp == null) {
            val newOtp = Otp(
                username = user.username,
                code = code
            )

            otpRepository.save(newOtp)
        } else {
            userOtp.updateCode(code)
        }
    }
}