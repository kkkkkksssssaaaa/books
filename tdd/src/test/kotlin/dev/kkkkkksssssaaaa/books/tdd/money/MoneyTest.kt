package dev.kkkkkksssssaaaa.books.tdd.money

import dev.kkkkkksssssaaaa.books.tdd.money.objects.Bank
import dev.kkkkkksssssaaaa.books.tdd.money.objects.Expression
import dev.kkkkkksssssaaaa.books.tdd.money.objects.Money
import dev.kkkkkksssssaaaa.books.tdd.money.objects.Sum
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MoneyTest {
    @Nested
    inner class DollarTest {
        @Test
        fun doTestMultiplication() {
            val five = Money.dollar(5)
            var product = five.times(2)

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
            val five = Money.franc(5)
            var product = five.times(2)

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

    @Nested
    inner class ReduceTest {
        @Test
        fun doTest() {
            val bank = Bank()
            bank.addRate("CHF", "USD", 2)

            val result = bank.reduced(Money.franc(2), "USD")
            assertEquals(Money.dollar(1), result)
        }
    }

    @Nested
    inner class MixedAdditionTest {
        @Test
        fun doTest() {
            val fiveBucks: Expression = Money.dollar(5)
            val tenFrancs: Expression = Money.franc(10)

            val bank = Bank()
            bank.addRate("CHF", "USD", 2)

            val result = bank.reduced(
                fiveBucks.plus(tenFrancs), "USD"
            )

            assertEquals(Money.dollar(10), result)
        }
    }

    @Test
    fun doTestEquality() {
        assertFalse(Money.franc(1).equals(Money.dollar(1)))
    }

    @Test
    fun doTestPlus() {
        assertEquals(
            Money.dollar(2),
            Money.dollar(1) + Money.dollar(1)
        )

        val bank = Bank()
        val sum = Sum(Money.dollar(3), Money.dollar(4))

        val reduced: Money = bank.reduced(sum, "USD")
        assertEquals(Money.dollar(7), reduced)
    }

    @Test
    fun doTestPlus2() {
        val bank = Bank()
        val result = bank.reduced(Money.dollar(1), "USD")

        assertEquals(Money.dollar(1), result)
    }
}