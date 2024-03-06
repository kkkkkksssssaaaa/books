package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Test

class Atom51 {
    @Test
    fun doTest() {
        val list = listOf(1, 2, 3, 4)

        println(">>> List:")
        println(
            list.filter(Int::isEven)
                .map(Int::square)
                .any(Int::lessThanTen)
        )

        println(">>> Sequence:")
        println(
            list.asSequence()
                .filter(Int::isEven)
                .map(Int::square)
                .any(Int::lessThanTen)
        )
    }
}

fun Int.isEven(): Boolean {
    println("isEven()")
    return this % 2 == 0
}

fun Int.square(): Int {
    println("square()")
    return this * this
}

fun Int.lessThanTen(): Boolean {
    println("lessThanTen()")
    return this < 10
}