package dev.retrotv.data.utils

import dev.retrotv.data.enums.OperatingSystem
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import kotlin.test.*
import java.math.RoundingMode
import java.util.*

class StringUtilsTest {

    @Test
    @DisplayName("addNewLine(?) 메소드 테스트")
    fun test_addNewLine_method1() {
        val os = System.getProperty("os.name").lowercase(Locale.getDefault())
        val value = "123456789"
        val returnValue = StringUtils.addNewLine(value)

        if (os.contains("win")) {
            assertEquals(value + "\r\n", returnValue)
        } else {
            assertEquals(value + "\n", returnValue)
        }
    }

    @Test
    @DisplayName("addNewLine(?, ?) 메소드 테스트")
    fun test_addNewLine_method2() {
        var os = OperatingSystem.WINDOWS
        val value = "123456789"
        var returnValue = StringUtils.addNewLine(value, os)

        assertEquals(value + "\r\n", returnValue)

        os = OperatingSystem.LINUX
        returnValue = StringUtils.addNewLine(value, os)

        assertEquals(value + "\n", returnValue)
    }

    @Test
    @DisplayName("removeNewLine 메소드 테스트")
    fun test_removeNewLine_method() {
        val value = "123456789\n"
        val returnValue = StringUtils.removeNewLine(value)

        assertNotEquals("123456789\n", returnValue)
        assertEquals("123456789", returnValue)
    }

    @Test
    @DisplayName("appendAll(?) 메소드 테스트")
    fun test_appendAll_array_parameter_method() {
        val arr = arrayOf("Hello", "World")
        val returnValue = StringUtils.appendAll(values = arr)

        assertNotEquals("Hello World", returnValue)
        assertEquals("HelloWorld", returnValue)
    }

    @Test
    @DisplayName("appendAll(?) 메소드 테스트")
    fun test_appendAll_dynamic_parameter_method() {
        val returnValue = StringUtils.appendAll("Hello", "World")

        assertNotEquals("Hello World", returnValue)
        assertEquals("HelloWorld", returnValue)
    }

    @Test
    @DisplayName("appendAll(?, ?, ?) 메소드 테스트")
    fun test_appendAll_three_parameter_method() {
        val returnValue = StringUtils.appendAll(values = arrayOf("Hello", "World"), delimiter = " ", useDelimiter = true)

        assertNotEquals("HelloWorld", returnValue)
        assertEquals("Hello World", returnValue)
    }

    @Test
    @DisplayName("masking 메소드 테스트")
    fun test_masking_method() {
        val returnValue = StringUtils.masking("123456-1234567", '#', 8, 13)
        assertEquals("123456-1######", returnValue)
    }

    @Test
    @DisplayName("decimalToString(?, ?) 메소드 테스트")
    fun test_decimalToString_two_parameter_method() {
        var returnValue = StringUtils.decimalToString((1.0/3.0), "#.##")
        assertEquals("0.33", returnValue)

        returnValue = StringUtils.decimalToString((5.0/3.0), "#.##")
        assertEquals("1.67", returnValue)
    }

    @Test
    @DisplayName("decimalToString(?, ?, ?) 메소드 테스트")
    fun test_decimalToString_three_parameter_method() {
        var returnValue = StringUtils.decimalToString((1.0/3.0), "#.##", RoundingMode.UP)
        assertEquals("0.34", returnValue)

        returnValue = StringUtils.decimalToString((5.0/3.0), "#.##", RoundingMode.DOWN)
        assertEquals("1.66", returnValue)
    }

    @Test
    @DisplayName("intToString 메소드 테스트")
    fun test_intToString_method() {
        var returnValue = StringUtils.intToString(1000000, "#,###")
        assertEquals("1,000,000", returnValue)

        returnValue = StringUtils.intToString(1000000, "#,###.00")
        assertEquals("1,000,000.00", returnValue)
    }

    @Test
    @DisplayName("scrambleChars 메소드 테스트")
    fun test_scrambleChars_method() {
        println(StringUtils.scrambleChars("Hello, World!"))
    }

    @Test
    @DisplayName("combineString 메소드 테스트")
    fun test_combineString_method() {
        var returnValue = StringUtils.combineStrings("Hello", "World")
        assertEquals("Hello World", returnValue)

        returnValue = StringUtils.combineStrings("Hello", "World", separator = '.')
        assertEquals("Hello.World", returnValue)
    }

    @Test
    @DisplayName("isNull 메소드 테스트")
    fun test_isNull_method() {
        assertTrue(StringUtils.isNull(null))
        assertFalse(StringUtils.isNull("Hello, World!"))
    }

    @Test
    @DisplayName("isEmpty 메소드 테스트")
    fun test_isEmpty_method() {
        assertTrue(StringUtils.isEmpty(null))
        assertTrue(StringUtils.isEmpty(""))
        assertFalse(StringUtils.isEmpty("Hello, World!"))
    }

    @Test
    @DisplayName("isBlank 메소드 테스트")
    fun test_isBlank_method() {
        assertTrue(StringUtils.isBlank(null))
        assertTrue(StringUtils.isBlank(""))
        assertTrue(StringUtils.isBlank(" "))
        assertFalse(StringUtils.isBlank("Hello, World!"))
    }
}