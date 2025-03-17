package dev.kkkkkksssssaaaa.books.tdd.money.objects

class Bank {
    private val rates = mutableMapOf<FromToPair, Int>()

    fun reduced(source: Expression, to: String): Money {
        if (source is Money) return source.reduce(this, to)

        val sum = source as Sum

        return sum.reduce(this, to)
    }

    fun rate(from: String, to: String): Int {
        val rate = rates[FromToPair(from, to)]

        return rate ?: 0
    }

    fun addRate(from: String, to: String, rate: Int) {
        rates[FromToPair(from, to)] = rate
    }
}