package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsTest {

    @Test
    @DisplayName("checkLength 메소드 테스트")
    void test_checkLength_method() {
        assertTrue(PasswordUtils.checkLength("This is password"));

        assertTrue(PasswordUtils.checkLength("This is password", 6));
        assertFalse(PasswordUtils.checkLength("This", 6));
        assertFalse(PasswordUtils.checkLength("This is password", 20));

        assertTrue(PasswordUtils.checkLength("This is password", 6, 20));
        assertFalse(PasswordUtils.checkLength("This is password", 6, 12));
        assertFalse(PasswordUtils.checkLength("This", 6, 12));
        assertFalse(PasswordUtils.checkLength("This is password and so long", 6, 12));
    }

    @Test
    @DisplayName("isInclude 메소드 테스트")
    void test_isInclude_method() {
        assertTrue(PasswordUtils.isInclude("This !s Passw0rd"));
        assertFalse(PasswordUtils.isInclude("this is password"));
        assertFalse(PasswordUtils.isInclude("THIS IS PASSWORD"));
        assertFalse(PasswordUtils.isInclude("This is password"));
        assertFalse(PasswordUtils.isInclude("This !s password"));
        assertFalse(PasswordUtils.isInclude("This is passw0rd"));
        assertTrue(PasswordUtils.isInclude("This is password", true, false, false, false));
        assertTrue(PasswordUtils.isInclude("This is password", true, true, false, false));
        assertTrue(PasswordUtils.isInclude("This is Passw0rd", true, true, true, false));
        assertTrue(PasswordUtils.isInclude("This !s Passw0rd", true, true, true, true));
    }
}
