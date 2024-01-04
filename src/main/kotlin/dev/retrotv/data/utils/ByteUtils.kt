@file:JvmName("ByteUtils")
package dev.retrotv.data.utils

import dev.retrotv.data.enums.EncodeFormat
import dev.retrotv.data.enums.EncodeFormat.*
import org.apache.commons.codec.DecoderException
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import java.nio.charset.Charset

fun toString(data: ByteArray): String = String(data)

fun toString(data: ByteArray, charset: Charset): String = String(data, charset)

fun toHexString(data: ByteArray): String = Hex.encodeHexString(data)

fun toBase64String(data: ByteArray): String = Base64.encodeBase64String(data)

fun isNull(data: ByteArray?): Boolean = data == null

fun isEmpty(data: ByteArray?): Boolean = isNull(data) || data?.size == 0

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
