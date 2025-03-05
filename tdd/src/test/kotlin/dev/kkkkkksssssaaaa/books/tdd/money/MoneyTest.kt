package dev.kkkkkksssssaaaa.books.tdd.money

import dev.kkkkkksssssaaaa.books.tdd.money.objects.Dollar
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MoneyTest {

    @Test
    fun doTestMultiplication() {
        val five = Dollar(5)
        var product = five.times(2)

        assertEquals(10, product.amount)

        product = five.times(3)
        assertEquals(15, product.amount)
    }

    @Test
    fun doTestEquality() {
        assertTrue(Dollar(1) == Dollar(1))
        assertFalse(Dollar(1) == Dollar(2))
    }
}