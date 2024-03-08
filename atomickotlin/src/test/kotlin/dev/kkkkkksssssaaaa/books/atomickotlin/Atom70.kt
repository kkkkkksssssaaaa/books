package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Atom70 {
    object JustOne {
        val n = 2
        fun f() = n * 10
        fun g() = this.n * 20
    }

    @Test
    fun doTest() {
        assertEquals(2, JustOne.n)
        assertEquals(20, JustOne.f())
        assertEquals(40, JustOne.g())
    }
}