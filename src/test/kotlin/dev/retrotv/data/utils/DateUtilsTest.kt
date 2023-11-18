package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class DateUtilsTest {

    @Test
    @DisplayName("isLeapYear 메소드 테스트")
    fun test_isLeapYear_method() {
        assertTrue(isLeapYear(2000))
        assertTrue(isLeapYear("2000"))
        assertTrue(isLeapYear(2024))
        assertTrue(isLeapYear("2024"))
        assertTrue(isLeapYear(2040))
        assertTrue(isLeapYear("2040"))
        assertTrue(isLeapYear(2040))
        assertTrue(isLeapYear("2040"))
        assertTrue(isLeapYear(2400))
        assertTrue(isLeapYear("2400"))
        assertFalse(isLeapYear(2023))
        assertFalse(isLeapYear("2023"))
        assertFalse(isLeapYear(2200))
        assertFalse(isLeapYear("2200"))
        assertFalse(isLeapYear(2100))
        assertFalse(isLeapYear("2100"))
    }

    @Test
    @DisplayName("getLastDay 메소드 테스트")
    fun test_getLastDay_method() {
        assertEquals(31, getLastDay(null, 3))
        assertEquals(30, getLastDay(2023, 4))
        assertEquals(28, getLastDay(2023, 2))
        assertEquals(29, getLastDay(2024, 2))
        assertEquals("31", getLastDay("0001", "3"))
        assertEquals("30", getLastDay("2023", "04"))
        assertEquals("28", getLastDay("2023", "2"))
        assertEquals("29", getLastDay("2024", "2"))
    }
}