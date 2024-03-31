package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsJavaTest {

    @Test
    @DisplayName("checkLength 메소드 테스트")
    void test_checkLength_method() {
        assertTrue(PasswordUtils.checkLength("This is password"));
        assertTrue(PasswordUtils.checkLength("This is password", 6));
        assertFalse(PasswordUtils.checkLength("This is password", 6, 12));
    }
}
