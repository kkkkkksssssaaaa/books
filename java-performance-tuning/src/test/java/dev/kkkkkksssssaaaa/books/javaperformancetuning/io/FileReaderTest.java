package dev.kkkkkksssssaaaa.books.javaperformancetuning.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class FileReaderTest {

    void doTest() throws Exception {
        String fileName = "some file";

        // 10MB 파일을 처리할 때 2,480초가 소요 되었 다고 한다
        // 이러한 결과의 원인은 문자열을 하나씩 읽도록 되어 있기 때문
        ArrayList result1 = readCharStream(fileName);

        // 400ms 가 소요된다
        // 문자열 단위로 읽는다고 한다
        // 다만 이것도 비효율적이기 때문에 잘 사용하지 않는다고 한다
        String result2 = readCharStreamWithBuffer(fileName);

        // 350ms 가 소요된다
        // 가장 빠르다!
        // 일반적으로 사용되는 방식
        ArrayList result3 = readBufferedReader(fileName);
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

    public String readCharStreamWithBuffer(String fileName) throws Exception {
        StringBuffer retSb = new StringBuffer();
        FileReader fr = null;

        try {
            fr = new FileReader(fileName);
            int bufferSize = 1024 * 1024;
            char[] readBuffer = new char[bufferSize];
            int resultSize = 0;

            while((resultSize = fr.read(readBuffer)) != -1) {
                if (resultSize == bufferSize) {
                    retSb.append(readBuffer);
                } else {
                    for (int i = 0; i < resultSize; i++) {
                        retSb.append(readBuffer[i]);
                    }
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

        return retSb.toString();
    }

    public ArrayList readBufferedReader(String fileName) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(fileName));
            String data;

            while((data = br.readLine()) != null) {
                list.add(data);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
        }

        return list;
    }
}
