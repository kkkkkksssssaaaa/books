package dev.kkkkkksssssaaaa.books.tdd.money.objects

class FromToPair(
    val from: String,
    val to: String
) {
    override fun equals(other: Any?): Boolean {
        if (!(other is FromToPair)) return false

        other as FromToPair

        return other.from == from && other.to == to
    }

    override fun hashCode(): Int {
        return 0
    }
}