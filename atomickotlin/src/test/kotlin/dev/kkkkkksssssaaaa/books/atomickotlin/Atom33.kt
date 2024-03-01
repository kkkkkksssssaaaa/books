package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Test

class Atom33 {
    @Test
    fun useWhen() {
        processInputs(
            listOf(
                "up",
                "d",
                "nowhere",
                "left",
                "right",
                "exit",
                "r"
            )
        )
    }

    internal class Coordinates {
        var x: Int = 0
            set(value) {
                println("x gets $value")
                field = value
            }

        var y: Int = 0
            set(value) {
                println("y gets $value")
                field = value
            }

        override fun toString() = "($x, $y)"
    }

    private fun processInputs(inputs: List<String>) {
        val coordinates: Coordinates = Coordinates()

        for (input in inputs) {
            when (input) {
                // 결과가 같다면 조건을 한 라인에 묶을 수 있다
                "up", "u" -> coordinates.y--
                "down", "d" -> coordinates.y++
                "left", "l" -> coordinates.x--
                "right", "r" -> {
                    println("Moving right")
                    coordinates.x++
                }
                "nowHere" -> {}
                "exit" -> return
                else -> println("bad input: $input")
            }
        }
    }
}