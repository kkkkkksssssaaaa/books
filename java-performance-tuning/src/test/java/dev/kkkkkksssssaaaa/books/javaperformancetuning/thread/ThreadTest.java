package dev.kkkkkksssssaaaa.books.javaperformancetuning.thread;

import org.junit.jupiter.api.Test;

class ThreadTest {

    @Test
    void doTest() {
        Sleep s = new Sleep();
        s.start();

        try {
            int count = 0;

            while(count < 5) {
                // 1초씩 기다린다
                s.join(500);
                count++;
                System.out.println("count = " + count);
            }

            // 스레드가 살아있는지 확인한다
            if (s.isAlive()) {
                s.interrupt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Sleep extends Thread {

    @Override
    public void run() {
        try {
            // 10초간 대기 후 종료한다
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Somebody stopped me T T");
        }
    }
}
