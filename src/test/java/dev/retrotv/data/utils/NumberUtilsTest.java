package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {

    @CsvSource({
        "3, 12, 3",
        "12, 48, 12",
        "9, 12, 3",
        "18, 45, 9",
        "7, 11, 1"
    })
    @ParameterizedTest(name = "greatestCommonDivisor({0}, {1}) 메소드 테스트")
    void test_greatestCommonDivisor_method(long val1, long val2, long val3) {
        assertEquals(val3, NumberUtils.greatestCommonDivisor(val1, val2));
    }

    @CsvSource({
        "3, 12, 3",
        "12, 48, 12",
        "9, 12, 3",
        "18, 45, 9",
        "7, 11, 1"
    })
    @ParameterizedTest(name = "greatestCommonDivisor({0}, {1}) 메소드 테스트")
    void test_greatestCommonDivisorInt_method(int val1, int val2, int val3) {
        assertEquals(val3, NumberUtils.greatestCommonDivisor(val1, val2));
    }

    @CsvSource({
        "12, 12, 3, 3",
        "48, 48, 24, 12",
        "36, 12, 36, 3",
        "90, 45, 30, 9",
        "22, 11, 2, 1"
    })
    @ParameterizedTest(name = "leastCommonMultiple({1}, {2}, {3}) 메소드 테스트")
    void test_leastCommonMultiple_method(long val1, long val2, long val3, long val4) {
        assertEquals(val1, NumberUtils.leastCommonMultiple(val2, val3, val4));
    }

    @CsvSource({
        "12, 12, 3, 3",
        "48, 48, 24, 12",
        "36, 12, 36, 3",
        "90, 45, 30, 9",
        "22, 11, 2, 1"
    })
    @ParameterizedTest(name = "leastCommonMultiple({1}, {2}, {3}) 메소드 테스트")
    void test_leastCommonMultipleInt_method(int val1, int val2, int val3, int val4) {
        assertEquals(val1, NumberUtils.leastCommonMultiple(val2, val3, val4));
    }

    @Test
    @DisplayName("isNull 메소드 테스트")
    void test_isNull_method() {
        Integer intValue = null;
        assertTrue(NumberUtils.isNull(intValue));
        intValue = 1;
        assertFalse(NumberUtils.isNull(intValue));

        Long longValue = null;
        assertTrue(NumberUtils.isNull(longValue));
        longValue = 1L;
        assertFalse(NumberUtils.isNull(longValue));

        Double doubleValue = null;
        assertTrue(NumberUtils.isNull(doubleValue));
        doubleValue = 1.0;
        assertFalse(NumberUtils.isNull(doubleValue));
    }
}
