package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Atom45 {
    @Test
    fun doTest() {
        val func = add(10)
        val result = func(20)
        println(result)
        assertEquals(result, 30)
    }

    // add 함수를 호출하면 결과는 함수를 반환한다. (Int) -> Int 가 반환형이다..
    fun add(x: Int): (Int) -> Int {
        return fun(y: Int): Int {
            // 반환되는 함수 몸체에서는 x 라는 변수는 반환 되는 함수인 익명 함수 내부에서 정의하지 않고, 익명 함수를 선언한 외부 함수인 add 에서 인자로 받는 함수이다.
            // 이렇게 상위 함수에 접근할 수 있는 함수를 클로저라고 한다.
            return x + y
        }
    }
}