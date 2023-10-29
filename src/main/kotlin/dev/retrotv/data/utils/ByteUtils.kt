@file:JvmName("ByteUtils")
package dev.retrotv.data.utils

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
