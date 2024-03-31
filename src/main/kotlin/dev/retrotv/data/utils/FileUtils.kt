package dev.retrotv.data.utils

import java.io.DataInputStream
import java.io.File
import java.io.IOException
import java.nio.file.Files

object FileUtils {
    @JvmStatic
    @Throws(IOException::class)
    fun read(file: File): ByteArray {
        var fileData: ByteArray

        try {
            DataInputStream(Files.newInputStream(file.toPath())).use { dis ->
                fileData = ByteArray(file.length().toInt())
                dis.readFully(fileData)
            }
        } catch (e: IOException) {
            throw IOException("파일을 읽어 들이는 과정에서 오류가 발생했습니다.")
        }

        return fileData
    }

    @JvmStatic
    fun isNull(file: File?): Boolean {
        return file == null
    }
}
