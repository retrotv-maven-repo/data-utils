package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsJavaTest {

    @Test
    @DisplayName("trim 테스트")
    void test_trim() {
        String message = "Hello, World!";

        String returnValue = StringUtils.trim(" Hello, World! ");
        assertEquals(message, returnValue);

        returnValue = StringUtils.trim(null);
        assertNull(returnValue);

        returnValue = StringUtils.trim(null, false);
        assertEquals("", returnValue);
    }
}
