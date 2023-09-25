package dev.kkkkkksssssaaaa.books.javaperformancetuning.pattern;

import javax.naming.InitialContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

    private static ServiceLocator me;

    private InitialContext ic;
    private Map cache;

    static {
        me = new ServiceLocator();
    }

    public ServiceLocator() {
        cache = Collections.synchronizedMap(new HashMap<>());
    }

    public static ServiceLocator getInstance() {
        return me;
    }

    public InitialContext getInitialContext() throws Exception {
        try {
            if (ic == null) {
                ic = new InitialContext();
            }
        } catch (Exception e) {
            throw e;
        }

        return ic;
    }
}
