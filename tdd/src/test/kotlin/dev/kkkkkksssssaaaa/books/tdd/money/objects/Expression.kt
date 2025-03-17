package dev.kkkkkksssssaaaa.books.tdd.money.objects

interface Expression {
    fun reduce(bank: Bank, to: String): Money
    fun plus(addend: Expression): Expression
}