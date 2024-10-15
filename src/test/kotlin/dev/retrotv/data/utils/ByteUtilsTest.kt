package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertTrue

class ByteUtilsTest {

    @Test
    @DisplayName("isEmpty 메소드 테스트")
    fun test_isEmpty_method() {
        val data: ByteArray = byteArrayOf()
        assertTrue(ByteUtils.isEmpty(data))
    }

    @Test
    @DisplayName("combineByteArray 메소드 테스트")
    fun test_combineByteArray_method() {
        val byteArray1 = ByteArray(8)
        val byteArray2 = ByteArray(16)
        val byteArray3 = ByteArray(4)
        byteArray1[0] = 1
        byteArray1[1] = 1
        byteArray1[2] = 1
        byteArray1[3] = 1
        byteArray1[4] = 1
        byteArray1[5] = 1
        byteArray1[6] = 1
        byteArray1[7] = 1

        byteArray2[0] = 0
        byteArray2[1] = 1
        byteArray2[2] = 0
        byteArray2[3] = 1
        byteArray2[4] = 0
        byteArray2[5] = 1
        byteArray2[6] = 0
        byteArray2[7] = 1
        byteArray2[8] = 0
        byteArray2[9] = 1
        byteArray2[10] = 0
        byteArray2[11] = 1
        byteArray2[12] = 0
        byteArray2[13] = 1
        byteArray2[14] = 0
        byteArray2[15] = 1

        byteArray3[0] = 0
        byteArray3[1] = 0
        byteArray3[2] = 0
        byteArray3[3] = 0

        ByteUtils.combineByteArrays(byteArray1, byteArray2, byteArray3)
    }
}