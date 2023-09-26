package dev.kkkkkksssssaaaa.books.javaperformancetuning.system;

import org.junit.jupiter.api.Test;

import java.util.Properties;

class SystemTest {

    @Test
    void arrayCopyTest() {
        String[] arr = new String[]{"AAA", "BBB", "CCC", "DDD", "EEE"};
        String[] copiedArr = new String[3];

        System.arraycopy(arr, 0, copiedArr, 0, 3);

        for (String value : copiedArr) {
            System.out.println("value = " + value);
        }
    }

    @Test
    void propertiesTest() {
        System.setProperty("JavaTuning", "Tune Lee");
        Properties prop = System.getProperties();

        prop.keySet()
            .stream()
            .iterator()
            .forEachRemaining(key -> {
                System.out.println("key = " + key);
                System.out.println("value = " + prop.get(key));
            });
    }
}
