package dev.kkkkkksssssaaaa.books.tdd.money.objects

class Dollar(
    val amount: Long,
) {
    fun times(multiplier: Long): Dollar {
        return Dollar(
            amount = this.amount * multiplier
        )
    }

    override fun hashCode(): Int {
        return this.amount.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Dollar && this.amount == other.amount
    }
}