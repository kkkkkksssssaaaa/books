package dev.kkkkkksssssaaaa.books.javaperformancetuning.string;

import org.junit.jupiter.api.Test;

class StringBufferTest {

    @Test
    void doTest1() {
        StringBuilder sb = new StringBuilder();
        sb.append("ABCDE");
        check(sb);
    }

    private void check(CharSequence cs) {
        StringBuffer sb = new StringBuffer(cs);
        System.out.println("sb.length() = " + sb.length());
    }
}
