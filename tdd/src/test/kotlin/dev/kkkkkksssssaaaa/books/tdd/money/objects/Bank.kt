package dev.kkkkkksssssaaaa.books.tdd.money.objects

class Bank {
    fun reduced(source: Expression, to: String): Money {
        if (source is Money) return source.reduce(to)

        val sum = source as Sum

        return sum.reduce(to)
    }
}