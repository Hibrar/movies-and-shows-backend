package com.version1.movies_and_shows_backend.seed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeedParseIntegerTest {
    @Test
    void testParseIntegerWithValidNumber() {
        assertEquals(123, Seed.parseInteger("123"));
    }

    @Test
    void testParseIntegerWithNegativeNumber() {
        assertEquals(-456, Seed.parseInteger("-456"));
    }

    @Test
    void testParseIntegerWithZero() {
        assertEquals(0, Seed.parseInteger("0"));
    }

    @Test
    void testParseIntegerWithNull() {
        assertEquals(0, Seed.parseInteger(null));
    }

    @Test
    void testParseIntegerWithEmptyString() {
        assertEquals(0, Seed.parseInteger(""));
    }

    @Test
    void testParseIntegerWithInvalidString() {
        assertEquals(0, Seed.parseInteger("abc"));
    }

    @Test
    void testParseIntegerWithWhitespace() {
        assertEquals(0, Seed.parseInteger("   "));
    }

    @Test
    void testParseIntegerWithDecimal() {
        assertEquals(0, Seed.parseInteger("12.34"));
    }
}
