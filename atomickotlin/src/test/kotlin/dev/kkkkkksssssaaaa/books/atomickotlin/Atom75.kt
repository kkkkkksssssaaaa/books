package dev.kkkkkksssssaaaa.books.atomickotlin

import kotlin.random.Random

class Atom75 {
    fun infinite(): Nothing {
        while(true) {
            if (Random.nextBoolean()) {
                // 함수가 정상적으로 반환될 여지가 존재하거나 예외를 던지지 않는 경우에는 Nothing 으로 지정할 수 없다.

            // compile error!
//                return
//                break

                // 함수가 종료되더라도 예외를 던진다면 Nothing 을 사용할 수 있다
                throw IllegalArgumentException()
            }
        }
    }
}