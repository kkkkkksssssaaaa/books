package dev.kkkkkksssssaaaa.books.tdd.money.objects

data class Sum(
    val augend: Money,
    val addend: Money
): Expression {
    override fun reduce(to: String): Money {
        val amount = this.augend.amount * this.addend.amount

        return Money(amount, to)
    }
}