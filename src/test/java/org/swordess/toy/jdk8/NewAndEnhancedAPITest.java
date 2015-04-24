package org.swordess.toy.jdk8;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NewAndEnhancedAPITest {

    @Test
    public void testInteger() {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(55, data.stream().reduce(Integer::sum).get().intValue());
    }

    @Test
    public void testString() {
        assertEquals("Java-is-cool", String.join("-", "Java", "is", "cool"));
    }

    @Test
    public void testFiles() throws IOException {
        Path pwd = new File(".").toPath();
        Files.list(pwd).forEach(System.out::println);
        Files.walk(pwd, 2).forEach(System.out::println);
    }

    @Test
    public void testArrays() {
        assertEquals("HelloWorld", Arrays
                .stream(new String[]{"Hello", "World"})
                .reduce(String::concat)
                .get());

        assertEquals(10, Arrays.stream(new int[]{1, 2, 3, 4}).reduce(0, Integer::sum));
        assertEquals(0, Arrays.stream(new int[0]).reduce(0, Integer::sum));
        try {
            Arrays.stream(new Integer[0]).reduce(Integer::sum).get();
            fail("NoSuchElementException is expected");
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void testPattern() {
        Pattern pattern = Pattern.compile("^\\w{2}-[a-zA-Z0-9]{8}$");
        String[] texts = { "abc", "AB-a1b2c3d4", "ab-1", "ab-12345678z", "CD-c1d2e3f4" };

        assertEquals(2, Arrays.stream(texts).filter(pattern.asPredicate()).count());
    }

}
