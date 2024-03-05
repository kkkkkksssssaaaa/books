package dev.kkkkkksssssaaaa.books.atomickotlin;

import org.junit.jupiter.api.Test;

class Atom45ForJava {

    Integer addOne(Integer b) {
        return 1 + b;
    }

    @Test
    void doTest() {
        Integer b = 4;
        Runnable r = () -> {
            Integer result = addOne(b);
            // compile error
//            b++;
        };
    }
}
