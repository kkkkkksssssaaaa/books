package dev.kkkkkksssssaaaa.books.tdd.money.objects

class Money(
    val amount: Long,
    val currency: String
): Expression {
    companion object {
        fun dollar(amount: Long) = Money(amount, currency = "USD")
        fun franc(amount: Long) = Money(amount, currency = "CHF")
    }

    fun times(multiplier: Int): Expression {
        return Money(
            amount = this.amount * multiplier,
            currency = currency
        )
    }

    override operator fun plus(addend: Expression): Expression {
        return Sum(
            this,
            addend
        )
    }

    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to)

        return Money(amount / rate, to)
    }

    override fun hashCode(): Int {
        return this.amount.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false

        return other is Money
            && this.currency == other.currency
            && this.amount == other.amount
    }
}