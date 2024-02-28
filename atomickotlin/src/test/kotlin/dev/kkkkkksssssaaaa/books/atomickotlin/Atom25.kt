package dev.kkkkkksssssaaaa.books.atomickotlin

class Atom25 {
    fun spreadOperator() {
        val arrays = intArrayOf(4, 5)
        // vararg 변수에 *를 붙이면, 마치 다음과 같다
        // 스프레드 연산자는 varargs 를 하나의 배열로 보지 않고, 각 요소들을 개별적으로 풀어 헤치는..... 것
        // varargs arrays: Int = intArrayOf(4, 5) == [4, 5]
        // *arrays == 4, 5
        sum(1, 2, 3, *arrays, 6)
    }

    private fun sum(vararg ints: Int): Int {
        return ints.sum()
    }
}