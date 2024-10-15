package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NumberUtilsTest {

    @CsvSource(
        "3, 12, 3",
        "12, 48, 12",
        "9, 12, 3",
        "18, 45, 9",
        "7, 11, 1"
    )
    @ParameterizedTest(name = "greatestCommonDivisor({0}, {1}) 메소드 테스트")
    fun test_greatestCommonDivisor_method(val1: Long, val2: Long, val3: Long) {
        assertEquals(val3, NumberUtils.greatestCommonDivisor(val1, val2))
    }

    @CsvSource(
        "3, 12, 3",
        "12, 48, 12",
        "9, 12, 3",
        "18, 45, 9",
        "7, 11, 1"
    )
    @ParameterizedTest(name = "greatestCommonDivisor({0}, {1}) 메소드 테스트")
    fun test_greatestCommonDivisorInt_method(val1: Int, val2: Int, val3: Int) {
        assertEquals(val3, NumberUtils.greatestCommonDivisor(val1, val2))
    }

    @CsvSource(
        "12, 12, 3, 3",
        "48, 48, 24, 12",
        "36, 12, 36, 3",
        "90, 45, 30, 9",
        "22, 11, 2, 1"
    )
    @ParameterizedTest(name = "leastCommonMultiple({1}, {2}, {3}) 메소드 테스트")
    fun test_leastCommonMultiple_method(val1: Long, val2: Long, val3: Long, val4: Long) {
        assertEquals(val1, NumberUtils.leastCommonMultiple(val2, val3, val4))
    }

    @CsvSource(
        "12, 12, 3, 3",
        "48, 48, 24, 12",
        "36, 12, 36, 3",
        "90, 45, 30, 9",
        "22, 11, 2, 1"
    )
    @ParameterizedTest(name = "leastCommonMultiple({1}, {2}, {3}) 메소드 테스트")
    fun test_leastCommonMultipleInt_method(val1: Int, val2: Int, val3: Int, val4: Int) {
        assertEquals(val1, NumberUtils.leastCommonMultiple(val2, val3, val4))
    }

    @Test
    @DisplayName("isNull 메소드 테스트")
    fun test_isNull_method() {
        var int: Int? = null
        assertTrue(NumberUtils.isNull(int))
        int = 1
        assertFalse(NumberUtils.isNull(int))

        var long: Long? = null
        assertTrue(NumberUtils.isNull(long))
        long = 1L
        assertFalse(NumberUtils.isNull(long))

        var double: Double? = null
        assertTrue(NumberUtils.isNull(double))
        double = 1.0
        assertFalse(NumberUtils.isNull(double))
    }
}