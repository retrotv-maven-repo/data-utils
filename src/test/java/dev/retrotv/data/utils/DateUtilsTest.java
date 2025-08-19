package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    @DisplayName("isLeapYear 메소드 테스트")
    void test_isLeapYear_method() {
        assertTrue(DateUtils.isLeapYear(2000));
        assertTrue(DateUtils.isLeapYear("2000"));
        assertTrue(DateUtils.isLeapYear(2024));
        assertTrue(DateUtils.isLeapYear("2024"));
        assertTrue(DateUtils.isLeapYear(2040));
        assertTrue(DateUtils.isLeapYear("2040"));
        assertTrue(DateUtils.isLeapYear(2040));
        assertTrue(DateUtils.isLeapYear("2040"));
        assertTrue(DateUtils.isLeapYear(2400));
        assertTrue(DateUtils.isLeapYear("2400"));
        assertFalse(DateUtils.isLeapYear(2023));
        assertFalse(DateUtils.isLeapYear("2023"));
        assertFalse(DateUtils.isLeapYear(2200));
        assertFalse(DateUtils.isLeapYear("2200"));
        assertFalse(DateUtils.isLeapYear(2100));
        assertFalse(DateUtils.isLeapYear("2100"));

        assertThrows(IllegalArgumentException.class, () -> DateUtils.isLeapYear("-1"));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.isLeapYear(-1));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.isLeapYear("10000"));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.isLeapYear(10000));
    }

    @Test
    @DisplayName("getLastDay 메소드 테스트")
    void test_getLastDay_method() {
        assertEquals(31, DateUtils.getLastDay(null, 3));
        assertEquals(30, DateUtils.getLastDay(2023, 4));
        assertEquals(28, DateUtils.getLastDay(2023, 2));
        assertEquals(29, DateUtils.getLastDay(2024, 2));
        assertEquals("31", DateUtils.getLastDay("0001", "3"));
        assertEquals("30", DateUtils.getLastDay("2023", "04"));
        assertEquals("28", DateUtils.getLastDay("2023", "2"));
        assertEquals("29", DateUtils.getLastDay("2024", "2"));

        assertThrows(IllegalArgumentException.class, () -> DateUtils.getLastDay(null, 2));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getLastDay(2023, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getLastDay(null, "2"));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getLastDay("2023", "0"));
    }

    @Test
    @DisplayName("addDay 메소드 테스트")
    void test_addDay_method() throws ParseException {
        assertEquals("20240101", DateUtils.addYMD("20231231", null, null, 1, "yyyyMMdd"));
        assertEquals("2024-01-01", DateUtils.addYMD("2023-12-31", null, null, 1, "yyyy-MM-dd"));

        assertEquals("20240131", DateUtils.addYMD("20231231", null, 1, null, "yyyyMMdd"));
        assertEquals("2023-12-30", DateUtils.addYMD("2023-11-30", null, 1, null, "yyyy-MM-dd"));
        assertEquals("2024-02-29", DateUtils.addYMD("2024-01-31", null, 1, null, "yyyy-MM-dd"));

        assertEquals("20241231", DateUtils.addYMD("20231231", 1, null, null, "yyyyMMdd"));
        assertEquals("2024-12-31", DateUtils.addYMD("2023-12-31", 1, null, null, "yyyy-MM-dd"));

        assertThrows(IllegalArgumentException.class, () -> DateUtils.addYMD("202312310000", 1, null, null, "yyyy-MM-dd"));
        assertThrows(ParseException.class, () -> DateUtils.addYMD("20231231", 1, null, null, "yyyy*MM*dd"));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.addYMD("abcdefgh", 1, null, null, "yyyyMMdd"));
    }

    @Test
    @DisplayName("stringToDate 메소드 테스트")
    @SuppressWarnings("java:S187")
    void test_stringToDate() throws ParseException {
        Date date = DateUtils.stringToDate("2023-12-31", "yyyy-MM-dd");
        assertEquals(2023, date.getYear() + 1900);
        assertEquals(11, date.getMonth());
        assertEquals(31, date.getDate());

        date = DateUtils.stringToDate("20231231");
        assertEquals(2023, date.getYear() + 1900);
        assertEquals(11, date.getMonth());
        assertEquals(31, date.getDate());
    }

    @Test
    @DisplayName("stringToLocalDate 메소드 테스트")
    void test_stringToLocalDate() {
        LocalDate date = DateUtils.stringToLocalDate("2023-12-31", "yyyy-MM-dd");
        assertEquals(2023, date.getYear());
        assertEquals(12, date.getMonthValue());
        assertEquals(31, date.getDayOfMonth());

        date = DateUtils.stringToLocalDate("20231231");
        assertEquals(2023, date.getYear());
        assertEquals(12, date.getMonthValue());
        assertEquals(31, date.getDayOfMonth());
    }

    @Test
    @DisplayName("stringToLocalDateTime 메소드 테스트")
    void test_stringToLocalDateTime() {
        LocalDateTime date = DateUtils.stringToLocalDateTime("2023-12-31 11:11:11", "yyyy-MM-dd HH:mm:ss");
        assertEquals(2023, date.getYear());
        assertEquals(12, date.getMonthValue());
        assertEquals(31, date.getDayOfMonth());

        date = DateUtils.stringToLocalDateTime("20231231 11:11:11");
        assertEquals(2023, date.getYear());
        assertEquals(12, date.getMonthValue());
        assertEquals(31, date.getDayOfMonth());
    }

    @Test
    @DisplayName("dateToString 메소드 테스트")
    void test_dateToString() throws ParseException {
        Date date1 = DateUtils.stringToDate("2023-12-31", "yyyy-MM-dd");
        assertEquals("2023-12-31", DateUtils.dateToString(date1, "yyyy-MM-dd"));
        date1 = DateUtils.stringToDate("20231231");
        assertEquals("2023-12-31", DateUtils.dateToString(date1, "yyyy-MM-dd"));
        assertEquals("20231231", DateUtils.dateToString(date1));

        LocalDate date2 = DateUtils.stringToLocalDate("2023-12-31", "yyyy-MM-dd");
        assertEquals("2023-12-31", DateUtils.dateToString(date2, "yyyy-MM-dd"));
        date2 = DateUtils.stringToLocalDate("20231231");
        assertEquals("2023-12-31", DateUtils.dateToString(date2, "yyyy-MM-dd"));
        assertEquals("20231231", DateUtils.dateToString(date2));

        LocalDateTime date3 = DateUtils.stringToLocalDateTime("2023-12-31 11:11:11", "yyyy-MM-dd HH:mm:ss");
        assertEquals("2023-12-31 11:11:11", DateUtils.dateToString(date3, "yyyy-MM-dd HH:mm:ss"));
        date3 = DateUtils.stringToLocalDateTime("20231231 11:11:11");
        assertEquals("2023-12-31 11:11:11", DateUtils.dateToString(date3, "yyyy-MM-dd HH:mm:ss"));
        assertEquals("20231231 11:11:11", DateUtils.dateToString(date3));
    }

    @Test
    @DisplayName("isNull 메소드 테스트")
    void test_isNull() {
        assertTrue(DateUtils.isNull(null));
    }
}
