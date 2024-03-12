package dev.kkkkkksssssaaaa.books.atomickotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Atom80 {
    data class Tag(var n: Int = 0) {
        var s: String =""
        fun increment() = ++n
    }

    @Test
    fun doTest() {
        /**
         * let(): 객체를 'it' 으로 접근하고 람다의 마지막 식의 값을 반환한다
         * return 을 쓰면 컴파일 에러가 난다..
         *
         * decompile to java
         *
         *       Tag var2 = new Tag(1);
         *       int var4 = false;
         *       var2.setS("let: " + var2.getN());
         *       int let = var2.increment();
         *       Assertions.assertEquals(2, let);
         * */
        val let = Tag(1).let {
            it.s = "let: ${it.n}"
            it.increment()
        }

        assertEquals(2, let)

        /**
         * decompile to java
         *
         *       Tag var3 = new Tag(2);
         *       int var5 = false;
         *       var3.setS("let: " + var3.getN());
         *       int let1 = var3.increment();
         *       Assertions.assertEquals(3, let1);
         * */
        val let1 = Tag(2).let { tag ->
            tag.s = "let: ${tag.n}"
            tag.increment()
        }

        assertEquals(3, let1)

        /**
         * run(): 객체를 'this' 로 접근하고 람다의 마지막 식의 값을 반환한다
         *
         * decompile to java
         *
         *       Tag var13 = new Tag(3);
         *       int var6 = false;
         *       var13.setS("run: " + var13.getN());
         *       int run = var13.increment();
         *       Assertions.assertEquals(4, run);
         * */
        val run = Tag(3).run {
            // this 키워드를 통해 인스턴스를 특정할 수 있다.
            this.s = "run: $n"
            this.increment()
        }

        assertEquals(4, run)

        /**
         * with(): 객체를 'this' 로 접근하고 람다의 마지막 식을 반환한다
         *
         * decompile to java
         *
         *       Tag var15 = new Tag(4);
         *       int var7 = false;
         *       var15.setS("with: " + var15.getN());
         *       int with = var15.increment();
         *       Assertions.assertEquals(5, with);
         * */
        val with = with(Tag(4)) {
            s = "with: $n"
            increment()
        }

        assertEquals(5, with)

        /**
         * apply(): 객체를 'this' 로 접근하고 변경된 객체를 다시 반환한다
         *
         * decompile to java
         *
         *       Tag var16 = new Tag(5);
         *       int var8 = false;
         *       var16.setS("apply: " + var16.getN());
         *       var16.increment();
         *       Assertions.assertTrue(true);
         * */
        val apply = Tag(5).apply {
            s = "apply: $n"
            increment()
        }

        assertTrue(apply is Tag)

        /**
         * also(): 객체를 'it' 으로 접근하고 변경된 객체를 다시 반환한다
         *
         * decompile to java
         *
         *       Tag var17 = new Tag(6);
         *       int var9 = false;
         *       var17.setS("also: " + var17.getN());
         *       var17.increment();
         *       Assertions.assertTrue(true);
         * */
        val also = Tag(6).also {
            it.s = "also: ${it.n}"
            it.increment()
        }

        assertTrue(also is Tag)

        /**
         * decompile to java
         *
         *       Tag var18 = new Tag(7);
         *       int var10 = false;
         *       var18.setS("also: " + var18.getN());
         *       var18.increment();
         *       Assertions.assertTrue(true);
         * */
        val also1 = Tag(7).also { tag ->
            tag.s = "also: ${tag.n}"
            tag.increment()
        }

        assertTrue(also1 is Tag)
    }
}