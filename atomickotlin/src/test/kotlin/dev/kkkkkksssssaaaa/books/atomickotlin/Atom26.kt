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

        // 합집합
        // {set1}.union({set2} 의 결과는 set1 에 set2 의 요소를 더한 결과를 반환함
        assertEquals(
            intSet.union(setOf(3, 4, 5, 6)),
            setOf(1, 2, 3, 4, 5, 6, 9)
        )

        // 교집합
        assertEquals(
            intSet intersect setOf(0, 1, 2, 7, 8),
            setOf(1, 2)
        )

        // 차집합1
        assertEquals(
            intSet subtract setOf(0, 1, 9, 10),
            setOf(2, 3, 4)
        )

        // 차집합2, - 기호로도 사용할 수 있다
        assertEquals(
            intSet - setOf(0, 1, 9, 10),
            setOf(2, 3, 4)
        )
    }
}