package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Atom26 {
    // set 은 집합..
    @Test
    fun set() {
        val intSet = setOf(1, 1, 2, 3, 9, 9, 4)

        assertEquals(intSet, setOf(1, 2, 3, 4, 9))

        // 요소가 포함 되어 있는지 검사
        assertTrue(1 in intSet)
        assertTrue(intSet.contains(1))
    }
}