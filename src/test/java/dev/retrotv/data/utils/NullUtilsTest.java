package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NullUtilsTest {

    @Test
    @DisplayName("isNull() 메서드 테스트")
    void test_isNull_method() {
        assertTrue(NullUtils.isNull(null));

        Object obj = new Object();
        assertFalse(NullUtils.isNull(obj));
    }
}
