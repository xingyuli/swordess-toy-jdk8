package org.swordess.toy.jdk8;

import org.junit.Test;

import java.lang.reflect.Method;

public class MethodParameterReflectionTest {

    @Test
    public void test() throws NoSuchMethodException {
        Method method = YeahMPR.class.getMethod("addOwner", String.class);
        // to retrieve the formal parameter name, option "-parameters" should
        // be used when javac, otherwise it will be "argN"
        // assertEquals("ownerName", method.getParameters()[0].getName());
    }

    public static interface YeahMPR {
        void addOwner(String ownerName);
    }

}
