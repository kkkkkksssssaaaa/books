package dev.kkkkkksssssaaaa.books.javaperformancetuning.system;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class CompareTimer {

    @Test
    void doTest() {
        for (int loop = 0; loop < 10; loop++) {
            checkNanoTime();
            checkCurrentTimeMillis();
        }
    }

    private DummyData dummy;

    public void checkCurrentTimeMillis() {
        long startTime = System.currentTimeMillis();
        dummy = timeMakeObjects();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("elapsedTime = " + elapsedTime);
    }

    public void checkNanoTime() {
        long startTime = System.nanoTime();
        dummy = timeMakeObjects();
        long endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime) / 1000000.0;

        System.out.println("elapsedTime = " + elapsedTime);
    }

    public DummyData timeMakeObjects() {
        HashMap<String, String> map = new HashMap<>(1000000);
        ArrayList<String> list = new ArrayList<>(1000000);

        return new DummyData(map, list);
    }
}

class DummyData {

    HashMap<String, String> map;
    ArrayList<String> list;

    public DummyData(HashMap<String, String> map, ArrayList<String> list) {
        this.map = map;
        this.list = list;
    }
}
