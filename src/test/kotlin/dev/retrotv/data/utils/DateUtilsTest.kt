package dev.retrotv.data.utils

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import java.text.ParseException
import kotlin.test.Test
import kotlin.test.*

class DateUtilsTest {

    @Test
    @DisplayName("isLeapYear 메소드 테스트")
    fun test_isLeapYear_method() {
        assertTrue(DateUtils.isLeapYear(2000))
        assertTrue(DateUtils.isLeapYear("2000"))
        assertTrue(DateUtils.isLeapYear(2024))
        assertTrue(DateUtils.isLeapYear("2024"))
        assertTrue(DateUtils.isLeapYear(2040))
        assertTrue(DateUtils.isLeapYear("2040"))
        assertTrue(DateUtils.isLeapYear(2040))
        assertTrue(DateUtils.isLeapYear("2040"))
        assertTrue(DateUtils.isLeapYear(2400))
        assertTrue(DateUtils.isLeapYear("2400"))
        assertFalse(DateUtils.isLeapYear(2023))
        assertFalse(DateUtils.isLeapYear("2023"))
        assertFalse(DateUtils.isLeapYear(2200))
        assertFalse(DateUtils.isLeapYear("2200"))
        assertFalse(DateUtils.isLeapYear(2100))
        assertFalse(DateUtils.isLeapYear("2100"))
    }

    @Test
    @DisplayName("getLastDay 메소드 테스트")
    fun test_getLastDay_method() {
        assertEquals(31, DateUtils.getLastDay(null, 3))
        assertEquals(30, DateUtils.getLastDay(2023, 4))
        assertEquals(28, DateUtils.getLastDay(2023, 2))
        assertEquals(29, DateUtils.getLastDay(2024, 2))
        assertEquals("31", DateUtils.getLastDay("0001", "3"))
        assertEquals("30", DateUtils.getLastDay("2023", "04"))
        assertEquals("28", DateUtils.getLastDay("2023", "2"))
        assertEquals("29", DateUtils.getLastDay("2024", "2"))
    }

    @Test
    @DisplayName("addDay 메소드 테스트")
    fun test_addDay_method() {
        assertEquals("20240101", DateUtils.addYMD("20231231", null, null,1, "yyyyMMdd"))
        assertEquals("2024-01-01", DateUtils.addYMD("2023-12-31", null, null,1, "yyyy-MM-dd"))

        assertEquals("20240131", DateUtils.addYMD("20231231", null, 1, null, "yyyyMMdd"))
        assertEquals("2023-12-30", DateUtils.addYMD("2023-11-30", null, 1, null, "yyyy-MM-dd"))
        assertEquals("2024-02-29", DateUtils.addYMD("2024-01-31", null, 1, null, "yyyy-MM-dd"))

        assertEquals("20241231", DateUtils.addYMD("20231231", 1, null,null, "yyyyMMdd"))
        assertEquals("2024-12-31", DateUtils.addYMD("2023-12-31", 1, null,null, "yyyy-MM-dd"))
    }

    @Test
    @DisplayName("stringToDate 메소드 테스트")
    fun test_stringToDate() {

    }

    @Test
    @DisplayName("dateToString 메소드 테스트")
    fun test_dateToString() {

    }
}