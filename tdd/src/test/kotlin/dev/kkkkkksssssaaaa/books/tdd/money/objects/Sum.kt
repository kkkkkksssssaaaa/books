package dev.kkkkkksssssaaaa.books.tdd.money.objects

data class Sum(
    val augend: Expression,
    val addend: Expression
): Expression {
    override fun reduce(bank: Bank, to: String): Money {
        val amount = this.augend.reduce(bank, to).amount +
            this.addend.reduce(bank, to).amount

        return Money(amount, to)
    }

    override fun plus(addend: Expression): Expression {
        TODO("Not yet implemented")
    }
}