package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Test

class Atom43 {
    @Test
    fun doTest() {
        val strings = mutableListOf<String>()
        // outer 가 label 의 이름이다.
        outer@ for (c in 'a'..'e') {
            println("c=$c")
            for (i in 1..9) {
                println("i=$i")
                if (i == 5) continue@outer
                if ("$c$i" == "c3") break@outer
                strings.add("$c$i")
            }
        }
    }
}