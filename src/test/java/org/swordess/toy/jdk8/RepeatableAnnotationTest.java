package org.swordess.toy.jdk8;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RepeatableAnnotationTest {

    @Test
    public void testCheckPresent() throws NoSuchMethodException {
        Method method = Yeah.class.getMethod("block");

        // Repeatable annotation should be checked against its Container type
        assertFalse(method.isAnnotationPresent(Schedule.class));
        assertTrue(method.isAnnotationPresent(Schedules.class));
    }

    @Test
    public void testRepeatableAnnotations() throws NoSuchMethodException {
        Method method = Yeah.class.getMethod("block");

        if (method.isAnnotationPresent(Schedules.class)) {
            Schedule[] ss = method.getAnnotationsByType(Schedule.class);
            assertEquals("08:00", ss[0].time());
            assertEquals("20:00", ss[1].time());

        } else {
            fail("should never reach");
        }
    }

    public static class Yeah {

        @Schedule(time = "08:00")
        @Schedule(time = "20:00")
        public void block() {
            System.out.println("block invoked");
        }

    }

    @Repeatable(Schedules.class)
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Schedule {
        String time();
    }

    // repeatable of Schedule annotation only allowed at method declaration
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public static @interface Schedules {
        Schedule[] value();
    }

}
