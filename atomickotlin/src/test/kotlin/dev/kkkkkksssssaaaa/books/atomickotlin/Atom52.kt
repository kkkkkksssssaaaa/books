package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Atom52 {
    @Test
    fun doTest() {

    }

    @Test
    fun labeledReturn() {
        val list = listOf(1, 2, 3, 4, 5)
        val value = 3
        var result = ""

        list.forEach tag@{
            println("it=$it")
            println("result=$result")
            result += "$it"

            if (it == value) {
                println("it is equal value!")

                // label 을 리턴하지 않는다면, 함수 자체가 반환되어 버리기 때문에 아무 것도 실행할 수 없는 상태라고 한다.
                // 그래서 label 이 붙은 데 까지만 반환하라고 지정하여 모든 연산이 수행 가능해진 듯 하다...?
                return@tag
            }
        }

        println(">>> final result=$result")

        assertEquals(result, "12345")
    }
}