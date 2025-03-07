package dev.kkkkkksssssaaaa.books.tdd.money.objects

class Money(
    val amount: Long,
    val currency: String
) {
    companion object {
        fun dollar(amount: Long) = Money(amount, currency = "USD")
        fun franc(amount: Long) = Money(amount, currency = "CHF")
    }

    fun times(multiplier: Int): Money {
        return Money(
            amount = this.amount * multiplier,
            currency = currency
        )
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