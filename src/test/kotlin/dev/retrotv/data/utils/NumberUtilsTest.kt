package dev.retrotv.data.utils

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals

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
        assertEquals(value3, greatestCommonDivisor(value1, value2));
    }

    @CsvSource(
        "3, 12, 12",
        "12, 48, 48",
        "9, 12, 36",
        "18, 45, 90",
        "7, 11, 77"
    )
    @ParameterizedTest(name = "lesatCommonMultiple({0}, {1}) 메소드 테스트")
    fun test_lesatCommonMultiple1_method(value1: Long, value2: Long, value3: Long) {
        assertEquals(value3, leastCommonMultiple(value1, value2));
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
        assertEquals(val4, leastCommonMultiple(value1, value2, value3));
    }
}