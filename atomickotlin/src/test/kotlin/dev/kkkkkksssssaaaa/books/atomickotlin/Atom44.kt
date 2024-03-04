package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Atom44 {
    @Test
    fun lambdaAndNamedArgs() {
        val list = listOf(9, 11, 23, 32)
        val toString = list.joinToString(
            separator = " ",
            // 이렇게 named argument 에도 람다를 사용할 수 있다.
            transform = { "[$it]" }
        )

        assertEquals(toString, "[9] [11] [23] [32]")
    }

    @Test
    fun underscore() {
        val list = listOf('a', 'b', 'c')
        val toString = list.mapIndexed {
            // 사용 하지 않는 람다 인자의 경우 밑 줄을 사용할 수 있다
                index, _ ->
            "[$index]"
        }

        assertEquals(toString, listOf("[0]", "[1]", "[2]"))
    }
}