package dev.kkkkkksssssaaaa.books.javaperformancetuning.io;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class FileReaderTest {

    void doTest() throws Exception {
        String fileName = "some file";
        // 10MB 파일을 처리할 때 2,480초가 소요 되었 다고 한다
        // 이러한 결과의 원인은 문자열을 하나씩 읽도록 되어 있기 때문
        ArrayList list1 = readCharStream(fileName);
    }

    public ArrayList readCharStream(String fileName) throws Exception {
        ArrayList<StringBuffer> list = new ArrayList<>();
        FileReader fr = null;

        try {
            fr = new FileReader(fileName);
            int data = 0;

            StringBuffer sb = new StringBuffer();

            while((data = fr.read()) != -1) {
                if (data == '\n' || data == '\r') {
                    list.add(sb);
                    sb = new StringBuffer();
                } else {
                    sb.append((char) data);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
            if (fr != null) {
                fr.close();
            }
        }

        return list;
    }
}
