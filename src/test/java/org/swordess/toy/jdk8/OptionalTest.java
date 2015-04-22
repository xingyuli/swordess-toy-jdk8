package org.swordess.toy.jdk8;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OptionalTest {

    @Test
    public void testEmpty() {
        assertFalse(Optional.empty().isPresent());
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetThrowsNoSuchElementExceptionIfNotPresent() {
        Optional.empty().get();
    }

    @Test
    public void testIfPresent() {
        Optional.of("string").ifPresent(v -> System.out.println(v.length()));
    }

    @Test
    public void testOf() {
        assertTrue(Optional.of("string").isPresent());
    }

    @Test(expected = NullPointerException.class)
    public void testOfThrowsNullPointerExceptionIfGivenValueIsNull() {
        Optional.of(null);
    }

    @Test
    public void testOfNullable() {
        assertFalse(Optional.ofNullable(null).isPresent());
        assertTrue(Optional.ofNullable("string").isPresent());
    }

    @Test
    public void testOrElse() {
        assertEquals("string", Optional.empty().orElse("string"));
        assertEquals("abc", Optional.of("abc").orElse("string"));
    }

    @Test
    public void testOrElseGet() {
        assertEquals("string", Optional.empty().orElseGet(() -> "string"));
        assertEquals("abc", Optional.of("abc").orElseGet(() -> "string"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrElseThrow() {
        Optional.empty().orElseThrow(() -> new IllegalArgumentException());
    }

    @Test
    public void testFilter() {
        Optional<Integer> result = Optional.of(5).filter(v -> v > 10);
        assertFalse(result.isPresent());

        result = Optional.of(20).filter((v) -> v > 10);
        assertTrue(result.isPresent());
    }

    @Test
    public void testMap() {
        Optional<Integer> result = Optional.of(10).map(v -> v * 2);
        assertEquals(20, result.get().intValue());
    }

}
