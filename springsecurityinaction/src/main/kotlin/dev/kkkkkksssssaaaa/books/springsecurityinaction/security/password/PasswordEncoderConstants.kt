package dev.kkkkkksssssaaaa.books.springsecurityinaction.security.password

enum class PasswordEncoderConstants(
    val key: String
) {
    BCRYPT("bcrypt"),
    SCRYPT("scrypt"),
    PLAIN_TEXT("plain"),
    SHA_512("sha512"),
    NO_OP("noop");

    fun wrappedKey(): String {
        return "{${this.key}}"
    }
}
