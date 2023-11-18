package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ValidUtilsTest {

    @Test
    @DisplayName("isDate 메소드 테스트")
    fun test_isDate_method() {
        assertTrue(isDate("20230911"))
        assertFalse(isDate("20230229"))

        assertTrue(isDate("2023-09-11"))
        assertFalse(isDate("2023-02-29"))
    }
}