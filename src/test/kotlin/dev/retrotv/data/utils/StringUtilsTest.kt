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

        returnValue = deciamlToString((5.0/3.0).toDouble(), "#.##")
        assertEquals("1.67", returnValue)
    }

    @Test
    @DisplayName("decimalToString(?, ?, ?) 메소드 테스트")
    fun test_decimalToString_three_parameter_method() {
        var returnValue = deciamlToString((1.0/3.0), "#.##", RoundingMode.UP)
        assertEquals("0.34", returnValue)

        returnValue = deciamlToString((5.0/3.0).toDouble(), "#.##", RoundingMode.DOWN)
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
}