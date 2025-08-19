package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ByteUtilsJavaTest {

    @Test
    @DisplayName("isEmpty 메소드 테스트")
    void test_isEmpty_method() {
        byte[] data = new byte[0];
        assertTrue(ByteUtils.isEmpty(data));
    }

    @Test
    @DisplayName("combineByteArray 메소드 테스트")
    void test_combineByteArray_method() {
        byte[] byteArray1 = new byte[8];
        byte[] byteArray2 = new byte[16];
        byte[] byteArray3 = new byte[4];

        byteArray1[0] = 1;
        byteArray1[1] = 1;
        byteArray1[2] = 1;
        byteArray1[3] = 1;
        byteArray1[4] = 1;
        byteArray1[5] = 1;
        byteArray1[6] = 1;
        byteArray1[7] = 1;

        byteArray2[0] = 0;
        byteArray2[1] = 1;
        byteArray2[2] = 0;
        byteArray2[3] = 1;
        byteArray2[4] = 0;
        byteArray2[5] = 1;
        byteArray2[6] = 0;
        byteArray2[7] = 1;
        byteArray2[8] = 0;
        byteArray2[9] = 1;
        byteArray2[10] = 0;
        byteArray2[11] = 1;
        byteArray2[12] = 0;
        byteArray2[13] = 1;
        byteArray2[14] = 0;
        byteArray2[15] = 1;

        byteArray3[0] = 0;
        byteArray3[1] = 0;
        byteArray3[2] = 0;
        byteArray3[3] = 0;

        byte[] result = ByteUtils.combineByteArrays(byteArray1, byteArray2, byteArray3);
        byte[] expected = new byte[byteArray1.length + byteArray2.length + byteArray3.length];
        System.arraycopy(byteArray1, 0, expected, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, expected, byteArray1.length, byteArray2.length);
        System.arraycopy(byteArray3, 0, expected, byteArray1.length + byteArray2.length, byteArray3.length);
        assertArrayEquals(expected, result);
    }
}
