package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Atom46 {
    @Test
    fun doTest() {
        val mutableList: MutableList<Int> =
            // MutableList 에서 받는 첫 번째 인자는 컬렉션의 크기이다
            // MutableList 에서 받는 두 번째 인자는 함수이며, 컬렉션의 크기만큼 반복하여 각 인덱스에 값을 초기화 한다
            // 함수 내부의 it 은 해당 컬렉션의 인덱스라고 한다
            MutableList(5, { 10 * (it + 1) })

        val simplyList: MutableList<Int> =
            // 함수의 인자 중 가장 마지막 인자가 함수라면, 블록문을 이렇게 사용할 수도 있다
            MutableList(5) {
                10 * (it + 1)
            }

        assertEquals(mutableList, listOf(10, 20, 30, 40, 50))
        assertEquals(simplyList, listOf(10, 20, 30, 40, 50))
    }
}