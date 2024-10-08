package dev.retrotv.data.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteUtilsJavaTest {

    @Test
    void test() {
        Assertions.assertTrue(ByteUtils.toString("".getBytes()).isEmpty());
    }
}
