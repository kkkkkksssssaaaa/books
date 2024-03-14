package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

class Atom81 {
    @Test
    fun doTest() {
        println("callA=${a(Person::class)}")
    }

    fun <T: Any> a(kClass: KClass<T>) {
        println("kClass=$kClass")
    }

    // compile error! T 의 타입 정보는 알 수 없다.
//    fun <T: Any> b() = a(T::class)

    fun <T: Any> c(kClass: KClass<T>) = a(kClass)
    // c 가 실행될 때에는 T 에 대한 타입 정보가 없기 때문에 굳이 굳이 kc 에서처럼 K 에 대한 타입 정보를 넘겨야 한다
    val kc = c(K::class)

    // reified 키워드가 붙은 제네릭은 타입 정보를 저장한다. reified 키워드를 쓰려면 inline 으로 선언 해야 한다.
    inline fun <reified T: Any> d() = a(T::class)
    // 그래서 굳이 타입 정보를 전달하지 않고 이런 식으로 써도 된다.
    val kd = d<K>()

    inline fun <reified T> check(t: Any) = t is T

    @Test
    fun doTest2() {
        // inline + reified 를 붙인 제네릭은 이렇게 이렇게 바로 타입 정보를 참조할 수 있게 된당
        assertTrue(check<String>("1"))
        assertFalse(check<Int>("1"))
        assertTrue(check<Long>(1L))
    }
}

class K

data class Person(
    val name: String,
    val age: Int
)