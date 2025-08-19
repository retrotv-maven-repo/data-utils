package dev.retrotv.data.utils;

import lombok.NonNull;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import java.nio.charset.Charset;

/**
 * 바이트 배열 관련 유틸리티 클래스입니다.
 *
 * @author  yjj8353
 * @since   1.0.0
 */
public final class ByteUtils {
    private ByteUtils() {
        throw new UnsupportedOperationException("ByteUtils는 인스턴스를 생성할 수 없습니다.");
    }

    /**
     * data를 문자열로 변환하여 반환합니다.
     *
     * @param data 문자열로 반환할 데이터
     * @return 변환된 문자열
     */
    public static String toString(byte[] data) {
        if (data == null) {
            return "";
        }

        return new String(data);
    }

    /**
     * data를 지정된 charset으로 인코딩한 문자열을 반환합니다.
     *
     * @param data 문자열로 반환할 데이터
     * @param charset 인코딩 시 지정할 캐릭터셋
     * @return 변환된 문자열
     */
    public static String toString(byte[] data, @NonNull Charset charset) {
        if (data == null) {
            return "";
        }

        return new String(data, charset);
    }

    /**
     * data를 Hex 타입 문자열로 변환하여 반환합니다.
     *
     * @param data Hex 타입 문자열로 반환할 데이터
     * @return 변환된 문자열
     */
    public static String toHexString(byte[] data) {
        if (data == null) {
            return "";
        }

        return Hex.encodeHexString(data);
    }

    /**
     * data를 Base64 타입 문자열로 변환하여 반환합니다.
     *
     * @param data Base64 타입 문자열로 반환할 데이터
     * @return 변환된 문자열
     */
    public static String toBase64String(byte[] data) {
        if (data == null) {
            return "";
        }

        return Base64.encodeBase64String(data);
    }

    /**
     * data가 null인지 확인합니다.
     *
     * @param data null 여부를 확인할 데이터
     * @return null 여부
     */
    public static boolean isNull(byte[] data) {
        return data == null;
    }

    /**
     * data가 null 혹은 크기가 0인지 확인합니다.
     *
     * @param data null 혹은 size == 0 여부를 확인할 데이터
     * @return null 혹은 size == 0 여부
     */
    public static boolean isEmpty(byte[] data) {
        return isNull(data) || data.length == 0;
    }

    /**
     * 인자로 받은 ByteArray(byte[])를 조합하고 반환합니다.
     *
     * @param byteArrays 조합할 ByteArray(byte[]) 집합
     * @return 조합된 ByteArray(byte[])
     */
    public static byte[] combineByteArrays(byte[]... byteArrays) {
        int arraySize = byteArrays.length;
        if (arraySize <= 1) {
            throw new IllegalArgumentException("인수로 들어오는 byte 배열의 개수는 2개 이상이어야 합니다.");
        }

        int totalByteSize = 0;
        for (byte[] arr : byteArrays) {
            totalByteSize += arr.length;
        }

        byte[] combinedByteArray = new byte[totalByteSize];
        int destPos = 0;
        for (byte[] arr : byteArrays) {
            System.arraycopy(arr, 0, combinedByteArray, destPos, arr.length);
            destPos += arr.length;
        }

        return combinedByteArray;
    }
}
