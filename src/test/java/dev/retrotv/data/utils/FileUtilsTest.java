package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    @DisplayName("read() 메서드 테스트")
    void test_read_method() throws IOException {
        File file = new File("src/test/resources/test.txt");
        byte[] content = FileUtils.read(file);
        assertNotNull(content);
        assertNotEquals(0, content.length, "파일 내용이 비어있지 않아야 합니다.");
    }

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
