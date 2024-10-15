package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals
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
    fun test_greatestCommonDivisor_method(value1: Long, value2: Long, value3: Long) {
        assertEquals(value3, NumberUtils.greatestCommonDivisor(value1, value2));
    }

    @CsvSource(
        "12, 12, 3, 3",
        "48, 48, 24, 12",
        "36, 12, 36, 3",
        "90, 45, 30, 9",
        "22, 11, 2, 1"
    )
    @ParameterizedTest(name = "leastCommonMultiple({1}, {2}, {3}) 메소드 테스트")
    fun test_leastCommonMultiple_method(value1: Long, value2: Long, value3: Long, value4: Long) {
        assertEquals(value1, NumberUtils.leastCommonMultiple(value2, value3, value4))
    }

    @CsvSource(
        "3, 12, 36, 36",
        "12, 48, 24, 48",
        "9, 12, 36, 36",
        "18, 45, 30, 90",
        "7, 11, 2, 154"
    )
    @ParameterizedTest(name = "lesatCommonMultiple({0}, {1}, {2}) 메소드 테스트")
    fun test_lesatCommonMultiple2_method(value1: Long, value2: Long, value3: Long, val4: Long) {
        assertEquals(val4, NumberUtils.leastCommonMultiple(value1, value2, value3));
    }

    @Test
    @DisplayName("isNull 메소드 테스트")
    fun test_isNull_method() {
        val int: Int? = null
        assertTrue(NumberUtils.isNull(int))

        val long: Long? = null
        assertTrue(NumberUtils.isNull(long))

        var double: Double? = null
        assertTrue(NumberUtils.isNull(double))
    }
}