package dev.kkkkkksssssaaaa.books.atomickotlin

class Atom59 {
}

internal open class Parent(
    val str: String,
    val num: Int
)

internal class Child(
    childStr: String,
    val childNum: Int
): Parent(childStr, childNum) {
    fun getSuperString(): String {
        return super.str
    }
}