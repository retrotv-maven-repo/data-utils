package dev.retrotv.data.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;

/**
 * 파일 관련 유틸리티 클래스입니다.
 *
 * @author  yjj8353
 * @since   1.0.0
 */
public final class FileUtils {
    private FileUtils() {
        throw new UnsupportedOperationException("FileUtils는 인스턴스를 생성할 수 없습니다.");
    }

    /**
     * 파일을 읽어들이고 ByteArray(byte[]) 형태로 반환하는 메소드
     *
     * @param file 읽어들일 파일
     * @return ByteArray(byte[])형으로 변환된 데이터
     * @throws IOException 파일을 읽어들이는 과정에서 오류가 발생하면 던져짐
     * @throws InvalidPathException 파일 인스턴스로부터 Path 객체를 생성할 수 없으면 던져짐
     * @throws SecurityException 파일 및 디렉터리 접근 권한이 없으면 던져짐
     */
    public static byte[] read(File file) throws IOException {
        byte[] fileData;
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(file.toPath()))) {
            fileData = new byte[(int) file.length()];
            dis.readFully(fileData);
        } catch (IOException ex) {
            throw new IOException("파일을 읽어 들이는 과정에서 오류가 발생했습니다.", ex);
        }

        return fileData;
    }

    /**
     * 파일 객체가 null인지 확인합니다.
     *
     * @param file null 여부를 확인할 File 객체
     * @return null 여부
     */
    public static boolean isNull(File file) {
        return file == null;
    }

    /**
     * 파일 객체가 null 혹은 크기가 0byte인지 확인합니다.
     *
     * @param file null 여부를 확인할 File 객체
     * @return null 혹은 0byte 여부
     * @throws SecurityException 파일 및 디렉터리 접근 권한이 없으면 던져짐
     */
    public static boolean isEmpty(File file) {
        return isNull(file) || file.length() == 0L;
    }
}