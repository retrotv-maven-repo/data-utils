package dev.retrotv.data.utils

import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import java.nio.charset.Charset

object ByteUtils {

    /**
     * data를 문자열로 변환하여 반환합니다.
     *
     * @param data 문자열로 반환할 데이터
     * @return 변환된 문자열
     */
    @JvmStatic
    fun toString(data: ByteArray): String = String(data)

    @JvmStatic
    fun toString(data: ByteArray, charset: Charset): String = String(data, charset)

    @JvmStatic
    fun toHexString(data: ByteArray): String = Hex.encodeHexString(data)

    @JvmStatic
    fun toBase64String(data: ByteArray): String = Base64.encodeBase64String(data)

    @JvmStatic
    fun isNull(data: ByteArray?): Boolean = data == null

    @JvmStatic
    fun isEmpty(data: ByteArray?): Boolean = isNull(data) || data?.size == 0

    @JvmStatic
    fun combineByteArray(vararg byteArrays: ByteArray) {
        val arraySize = byteArrays.size
        require(arraySize > 1) { "인수로 들어오는 byte 배열의 개수는 2개 이상이어야 합니다." }

        var totalByteSize = 0
        byteArrays.forEach { totalByteSize += it.size }

        val combinedByteArray = ByteArray(totalByteSize)
        var destPos = 0
        byteArrays.forEach {
            System.arraycopy(it, 0, combinedByteArray, destPos, it.size)
            destPos += it.size
        }
    }
}