@file:JvmName("ByteUtils")
package dev.retrotv.data.utils

import dev.retrotv.data.enums.EncodeFormat
import dev.retrotv.data.enums.EncodeFormat.*
import org.apache.commons.codec.DecoderException
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex

fun binaryToHex(data: ByteArray): String {
    return Hex.encodeHexString(data)
}

fun binaryToBase64(data: ByteArray): String {
    return Base64.encodeBase64String(data)
}

@Throws(DecoderException::class)
fun hexToBinary(hex: String): ByteArray {
    return Hex.decodeHex(hex)
}

fun base64ToBinary(base64: String): ByteArray {
    return Base64.decodeBase64(base64)
}

fun binaryEncode(encodeFormat: EncodeFormat, data: ByteArray): String {
    return when (encodeFormat) {
        BASE64 -> binaryToBase64(data)
        HEX -> binaryToHex(data)
    }
}

fun isNull(data: ByteArray?): Boolean {
    return data == null
}

fun isEmpty(data: ByteArray?): Boolean {
    return isNull(data) || data?.size == 0
}

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
