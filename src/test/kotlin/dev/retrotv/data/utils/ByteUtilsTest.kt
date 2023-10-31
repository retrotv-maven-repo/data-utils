package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertTrue

class ByteUtilsTest {

    @Test
    @DisplayName("isEmpty 메소드 테스트")
    fun test_isEmpty_method() {
        val data: ByteArray = byteArrayOf()
        assertTrue(isEmpty(data))
    }
}