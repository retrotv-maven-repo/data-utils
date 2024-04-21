package dev.retrotv.data.utils

import java.io.DataInputStream
import java.io.File
import java.io.IOException
import java.nio.file.Files

object FileUtils {

    /**
     * 파일을 읽어들이고 ByteArray(byte[]) 형태로 반환하는 메소드
     *
     * @param file 읽어들일 파일
     * @return ByteArray(byte[])형으로 변환된 데이터
     */
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

    /**
     * 파일 객체가 null인지 확인합니다.
     *
     * @param file null 여부를 확인할 File 객체
     * @return null 여부
     */
    @JvmStatic
    fun isNull(file: File?): Boolean = file == null

    /**
     * 파일 객체가 null 혹은 크기가 0byte인지 확인합니다.
     *
     * @param file null 여부를 확인할 File 객체
     * @return null 혹은 0byte 여부
     */
    @JvmStatic
    fun isEmpty(file: File?): Boolean = isNull(file) || (file?.length() == 0L)
}
