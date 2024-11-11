package dev.retrotv.data.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.io.File
import kotlin.test.Test

class FileUtilsTest {

    @Test
    @DisplayName("isNull() 메서드 테스트")
    fun test_isNull_method() {
        var file: File? = null
        assertTrue(FileUtils.isNull(file))

        file = File("")
        assertFalse(FileUtils.isNull(file))
    }

    @Test
    @DisplayName("isEmpty() 메서드 테스트")
    fun test_isEmpty_method() {
        var file: File? = null
        assertTrue(FileUtils.isEmpty(file))

        file = File("")
        assertTrue(FileUtils.isEmpty(file))
    }
}