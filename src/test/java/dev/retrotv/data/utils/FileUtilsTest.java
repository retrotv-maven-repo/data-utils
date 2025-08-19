package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    @DisplayName("isNull() 메서드 테스트")
    void test_isNull_method() {
        assertTrue(FileUtils.isNull(null));

        File file = new File("");
        assertFalse(FileUtils.isNull(file));
    }

    @Test
    @DisplayName("isEmpty() 메서드 테스트")
    void test_isEmpty_method() {
        assertTrue(FileUtils.isEmpty(null));

        File file = new File("");
        assertTrue(FileUtils.isEmpty(file));
    }
}
