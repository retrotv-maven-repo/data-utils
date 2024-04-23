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

    /**
     * data를 지정된 charset으로 인코딩한 문자열을 반환합니다.
     *
     * @param data 문자열로 반환할 데이터
     * @param charset 인코딩 시 지정할 캐릭터셋
     * @return 변환된 문자열
     */
    @JvmStatic
    fun toString(data: ByteArray, charset: Charset): String = String(data, charset)

    /**
     * data를 Hex 타입 문자열로 변환하여 반환합니다.
     *
     * @param data Hex 타입 문자열로 반환할 데이터
     * @return 변환된 문자열
     */
    @JvmStatic
    fun toHexString(data: ByteArray): String = Hex.encodeHexString(data)

    /**
     * data를 Base64 타입 문자열로 변환하여 반환합니다.
     *
     * @param data Base64 타입 문자열로 반환할 데이터
     * @return 변환된 문자열
     */
    @JvmStatic
    fun toBase64String(data: ByteArray): String = Base64.encodeBase64String(data)

    /**
     * data가 null인지 확인합니다.
     *
     * @param data null 여부를 확인할 데이터
     * @return null 여부
     */
    @JvmStatic
    fun isNull(data: ByteArray?): Boolean = data == null

    /**
     * data가 null 혹은 크기가 0인지 확인합니다.
     *
     * @param data null 혹은 size == 0 여부를 확인할 데이터
     * @return null 혹은 size == 0 여부
     */
    @JvmStatic
    fun isEmpty(data: ByteArray?): Boolean = isNull(data) || data?.size == 0

    /**
     * ByteArray(byte[])를 조합하고 반환합니다.
     *
     * @param byteArrays 조합할 ByteArray(byte[]) 집합
     */
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