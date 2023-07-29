package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StringUtilsTest {

    @Test
    @DisplayName("addNewLine 메소드 테스트")
    fun tets_addNewLine_method() {
        val value = "123456789"
        val returnValue = addNewLine(value)

        assertEquals(value + "\n", returnValue)
    }

    @Test
    @DisplayName("removeNewLine 메소드 테스트")
    fun tets_removeNewLine_method() {
        val value = "123456789\n"
        val returnValue = removeNewLine(value)

        assertNotEquals("123456789\n", returnValue)
        assertEquals("123456789", returnValue)
    }
}