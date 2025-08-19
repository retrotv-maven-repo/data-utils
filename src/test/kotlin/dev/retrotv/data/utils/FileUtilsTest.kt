package dev.retrotv.data.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.io.File
import kotlin.test.Test

class FileUtilsTest {

    @Test
    @DisplayName("isNull() 메서드 테스트")
    fun test_isNull_method() {
        assertTrue(FileUtils.isNull(null))

        val file: File = File("")
        assertFalse(FileUtils.isNull(file))
    }

    @Test
    @DisplayName("isEmpty() 메서드 테스트")
    fun test_isEmpty_method() {
        assertTrue(FileUtils.isEmpty(null))

        val file: File = File("")
        assertTrue(FileUtils.isEmpty(file))
    }
}