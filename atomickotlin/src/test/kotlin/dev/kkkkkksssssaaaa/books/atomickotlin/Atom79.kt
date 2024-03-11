package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Atom79 {
    // va 라는 이름의 함수를 정의하고 파라미터로 String 과 Int 를 전달 받아 String 을 반환한다
    private val va: (String, Int) -> String = { str, n ->
        // "Vanbo" 라는 문자열을 전달 받아 n 번 만큼 반복한다.
        // "Vanbo" 라는 문자열을 전달 받아 n 번 만큼 반복한 뒤, 앞서 실행된 결과 값에 문자열을 붙여서 반환한다.
        str.repeat(n) + str.repeat(n)
        // 단순한 람다 구문이라 this 를 사용하면 예의 그 자바처럼은 동작하지 않고, 람다 함수 그 자체를 가리키게 된다.. 인텔리제이가 알랴줌
    }

    // String 의 확장 함수 vb 를 정의한다.
    // vb 는 Int 를 파라미터로 전달 받아 String 을 반환하는 함수이다.
    // 확장 함수이기 때문에 this 키워드를 사용하여 해당 함수가 실행되는 시점의 인스턴스를 식별할 수 있다.
    private val vb: String.(Int) -> String = {
        this.repeat(it) + repeat(it)
    }

    // 이러한 형식의 확장 람다를 수신 객체가 지정된 함수 리터럴.... 이라고 말한단다
    // 다행히 이런 형식은 흔하지 않다...... f2() 처럼 함수의 파라미터로 확장 람다를 사용하는 경우가 많다
    private val vc: String.(Int, String, Boolean) -> Boolean = { i, s, b ->
        println("this=$this")
        println("number=$i")
        println("string=$s")
        println("return flag=$b")
        b
    }

    // 평범하게 A, B 인자를 받아 Int 를 반환하는 람다
    fun f1(lambda: (A, B) -> Int) {
        val a: A = A()
        val b: B = B()

        lambda(a, b)
    }

    // 람다 인자를 받는데, 이 람다는 A 의 확장 함수이고 인자는 B 를 받은 뒤, 연산 결과인 Int 를 반환한다.
    fun f2(lambda: A.(B) -> Int) {
        val a: A = A()

        a.lambda(B())
    }

    @Test
    fun doTest() {
        assertEquals(va("Vanbo", 2), "VanboVanboVanboVanbo")
        assertEquals("Vanbo".vb(2), "VanboVanboVanboVanbo")
        assertEquals(vb("Vanbo", 2), "VanboVanboVanboVanbo")

        // "Str" 은 수신 객체가 된다. this 를 통해 수신 객체에 접근할 수 있다.
        "Str".vc(1, "w", true)
//        "Vanbo".va(2) compile error!

        f1 { aa, bb ->
            aa.af() + bb.bf()
        }

        f2 {
            // this : A
            // it : B
            af() + it.bf()
        }
    }

    class A {
        fun af() = 1
    }

    class B {
        fun bf() = 2
    }
}