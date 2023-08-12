package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.RoundingMode
import java.util.*

class StringUtilsTest {

    @Test
    @DisplayName("addNewLine 메소드 테스트")
    fun test_addNewLine_method() {
        val os = System.getProperty("os.name").lowercase(Locale.getDefault())
        val value = "123456789"
        val returnValue = addNewLine(value)

        if (os.contains("win")) {
            assertEquals(value + "\r\n", returnValue)
        } else {
            assertEquals(value + "\n", returnValue)
        }
    }

    @Test
    @DisplayName("removeNewLine 메소드 테스트")
    fun test_removeNewLine_method() {
        val value = "123456789\n"
        val returnValue = removeNewLine(value)

        assertNotEquals("123456789\n", returnValue)
        assertEquals("123456789", returnValue)
    }

    @Test
    @DisplayName("appendAll(?) 메소드 테스트")
    fun test_appendAll_array_parameter_method() {
        val arr = arrayOf("Hello", "World")
        val returnValue = appendAll(values = arr)

        assertNotEquals("Hello World", returnValue)
        assertEquals("HelloWorld", returnValue)
    }

    @Test
    @DisplayName("appendAll(?) 메소드 테스트")
    fun test_appendAll_dynamic_parameter_method() {
        val returnValue = appendAll("Hello", "World")

        assertNotEquals("Hello World", returnValue)
        assertEquals("HelloWorld", returnValue)
    }

    @Test
    @DisplayName("appendAll(?, ?, ?) 메소드 테스트")
    fun test_appendAll_three_parameter_method() {
        val returnValue = appendAll(values = arrayOf("Hello", "World"), delimiter = " ", useDelimiter = true)

        assertNotEquals("HelloWorld", returnValue)
        assertEquals("Hello World", returnValue)
    }

    @Test
    @DisplayName("masking 메소드 테스트")
    fun test_masking_method() {
        val returnValue = masking("123456-1234567", '#', 8, 13)
        assertEquals("123456-1######", returnValue)
    }

    @Test
    @DisplayName("decimalToString(?, ?) 메소드 테스트")
    fun test_decimalToString_two_parameter_method() {
        var returnValue = deciamlToString((1.0/3.0), "#.##")
        assertEquals("0.33", returnValue)

        returnValue = deciamlToString((5.0/3.0), "#.##")
        assertEquals("1.67", returnValue)
    }

    @Test
    @DisplayName("decimalToString(?, ?, ?) 메소드 테스트")
    fun test_decimalToString_three_parameter_method() {
        var returnValue = deciamlToString((1.0/3.0), "#.##", RoundingMode.UP)
        assertEquals("0.34", returnValue)

        returnValue = deciamlToString((5.0/3.0), "#.##", RoundingMode.DOWN)
        assertEquals("1.66", returnValue)
    }

    @Test
    @DisplayName("intToString 메소드 테스트")
    fun test_intToString_method() {
        var returnValue = intToString(1000000, "#,###")
        assertEquals("1,000,000", returnValue)

        returnValue = intToString(1000000, "#,###.00")
        assertEquals("1,000,000.00", returnValue)
    }

    @Test
    @DisplayName("isIncludeEnglish 메소드 테스트")
    fun test_isIncludeEnglish_method() {
        var returnValue = isIncludeEnglish("12가31b2나123354aslkwne1")
        assertTrue(returnValue)

        returnValue = isIncludeEnglish("가나다라11230345!@#!@$")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isIncludeLowerCaseEnglish 메소드 테스트")
    fun test_isIncludeLowerCaseEnglish_method() {
        var returnValue = isIncludeLowerCaseEnglish("12가31b2나123354aslkwne1")
        assertTrue(returnValue)

        returnValue = isIncludeLowerCaseEnglish("가나다라11230345!@#!ASSDBWER@$")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isIncludeUpperCaseEnglish 메소드 테스트")
    fun test_isIncludeUpperCaseEnglish_method() {
        var returnValue = isIncludeUpperCaseEnglish("12가31B2나123354ASDQEFG1")
        assertTrue(returnValue)

        returnValue = isIncludeUpperCaseEnglish("가asd나다라112303fdb45!@#d!@$")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isIncludeSpecialCharacter 메소드 테스트")
    fun test_isIncludeSpecialCharacter_method() {
        var returnValue = isIncludeSpecialCharacter("12가31b2!@#!나1233545asl&*)kwne1")
        assertTrue(returnValue)

        returnValue = isIncludeSpecialCharacter("가나다라11230345aslqASDQW")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isIncludeKorean 메소드 테스트")
    fun test_isIncludeKorean_method() {
        var returnValue = isIncludeKorean("b12가312나123354aslkwn!@#ee")
        assertTrue(returnValue)

        returnValue = isIncludeKorean("qwodnosfmlq2102983eome!@#rhmdlfbs")
        assertFalse(returnValue)
    }
}