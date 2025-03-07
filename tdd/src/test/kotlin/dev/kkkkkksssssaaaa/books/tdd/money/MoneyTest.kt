package dev.kkkkkksssssaaaa.books.tdd.money

import dev.kkkkkksssssaaaa.books.tdd.money.objects.Money
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MoneyTest {
    @Nested
    inner class DollarTest {
        @Test
        fun doTestMultiplication() {
            val five: Money = Money.dollar(5)
            var product: Money = five.times(2)

            assertEquals(Money.dollar(10), product)

            product = five.times(3)
            assertEquals(Money.dollar(15), product)
        }

        @Test
        fun doTestEquality() {
            assertTrue(Money.dollar(1) == Money.dollar(1))
            assertFalse(Money.dollar(1) == Money.dollar(2))
        }
    }

    @Nested
    inner class FrancTest {
        @Test
        fun doTestMultiplication() {
            val five: Money = Money.franc(5)
            var product: Money = five.times(2)

            assertEquals(Money.franc(10), product)

            product = five.times(3)
            assertEquals(Money.franc(15), product)
        }

        @Test
        fun doTestEquality() {
            assertTrue(Money.franc(1) == Money.franc(1))
            assertFalse(Money.franc(1) == Money.franc(2))
        }
    }

    @Nested
    inner class CurrencyTest {
        @Test
        fun doTest() {
            assertEquals("USD", Money.dollar(1).currency)
            assertEquals("CHF", Money.franc(1).currency)
            assertTrue(Money(10, "USD") == Money(10, "USD"))
            assertFalse(Money(10, "USD") == Money(10, "CHF"))
        }
    }

    @Test
    fun doTestEquality() {
        assertFalse(Money.franc(1).equals(Money.dollar(1)))
    }
}