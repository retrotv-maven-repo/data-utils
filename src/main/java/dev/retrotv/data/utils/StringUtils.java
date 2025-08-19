package dev.retrotv.data.utils;

import dev.retrotv.data.enums.OperatingSystem;
import lombok.NonNull;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 문자열 관련 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 문자열을 ByteArray(byte[]), Hex, Base64 등 다양한 형식으로 변환하고,
 * 문자열 조작 및 포맷팅 기능을 제공합니다.
 * </p>
 *
 * @author yjj8353
 * @since 1.0.0
 */
public final class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException("StringUtils는 인스턴스화 할 수 없습니다.");
    }

    /**
     * 문자열을 ByteArray(byte[])형태로 변환하고 반환합니다.
     *
     * @param value ByteArray(byte[])로 변환할 문자열
     * @return 변환된 바이트 배열
     */
    public static byte[] toByteArray(String value) {
        return value.getBytes();
    }

    /**
     * 문자열을 정해진 캐릭터 셋의 ByteArray(byte[])형태로 변환하고 반환합니다.
     *
     * @param value ByteArray(byte[])로 변환할 문자열
     * @param charset ByteArray(byte[])로 변환시 사용할 캐릭터 셋
     * @return 변환된 바이트 배열
     */
    public static byte[] toByteArray(String value, Charset charset) {
        return org.apache.commons.codec.binary.StringUtils.getBytesUnchecked(value, charset.name());
    }

    /**
     * Hex 형식의 문자열을 ByteArray(byte[])형태로 변환하고 반환합니다.
     *
     * @param hex Hex 형식의 문자열
     * @return 변환된 바이트 배열
     * @exception DecoderException 디코딩 실패 시 던져짐
     */
    public static byte[] hexToByteArray(String hex) throws DecoderException {
        return Hex.decodeHex(hex);
    }

    /**
     * Base64 형식의 문자열을 ByteArray(byte[])형태로 변환하고 반환합니다.
     *
     * @param base64 Base64 형식의 문자열
     * @return 변환된 바이트 배열
     */
    public static byte[] base64ToByteArray(String base64) {
        return Base64.decodeBase64(base64);
    }

    /**
     * Hex 형식의 문자열을 Base64 형식의 문자열로 변환하고 반환합니다.
     *
     * @param hex Hex 형식의 문자열
     * @return 변환된 Base64 형식의 문자열
     */
    public static String hexToBase64(String hex) throws DecoderException {
        byte[] bytes = hexToByteArray(hex);
        return Base64.encodeBase64String(bytes);
    }

    /**
     * Base64 형식의 문자열을 Hex 형식의 문자열로 변환하고 반환합니다.
     *
     * @param base64 Base64 형식의 문자열
     * @return 변환된 Hex 형식의 문자열
     */
    public static String base64ToHex(String base64) {
        byte[] bytes = base64ToByteArray(base64);
        return Hex.encodeHexString(bytes);
    }

    /**
     * <pre>
     * 문자열 끝에 개행문자를 추가하고 반환합니다.
     * Windows 계열의 운영체제인 경우 "\r\n"을, 그 외의 운영체제는 "\n"을 문자열 끝에 추가합니다.
     * </pre>
     *
     * @param value 개행문자를 추가할 문자열
     * @return 개행문자가 추가된 문자열
     */
    public static String addNewLine(String value) {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win") ? value + "\r\n" : value + "\n";
    }

    /**
     * <pre>
     * 문자열 끝에 개행문자를 추가하고 반환합니다.
     * targetOS에 맞춰 적절한 개행문자를 문자열 끝에 추가합니다.
     * </pre>
     *
     * @param value 개행문자를 추가할 문자열
     * @param targetOS 추가될 문자열을 결정할 운영체제 정보
     * @return 개행문자가 추가된 문자열
     */
    public static String addNewLine(String value, OperatingSystem targetOS) {
        return targetOS == OperatingSystem.WINDOWS ? value + "\r\n" : value + "\n";
    }

    /**
     * 문자열에 포함된 개행문자("\r", "\n")를 모두 제거하고 반환합니다.
     *
     * @param value 개행문자를 제거할 문자열
     * @return 개행문자가 제거된 문자열
     */
    public static String removeNewLine(String value) {
        return value.replace("\r", "").replace("\n", "");
    }

    /**
     * <pre>
     * 인자로 받은 모든 문자열을 순차적으로 조합한 뒤, 반환합니다.
     * </pre>
     *
     * @param values 조합할 복수의 문자열
     * @return 조합된 하나의 문자열
     */
    public static String appendAll(String[] values) {
        StringBuilder sb = new StringBuilder();
        for (String v : values) {
            sb.append(v);
        }
        return sb.toString();
    }

    /**
     * <pre>
     * 인자로 받은 모든 문자열 사이에 구분자와 함께 순차적으로 조합한 뒤, 반환합니다.
     * values 인자로 받은 마지막 문자열 끝에는 구분자가 조합되지 않습니다.
     * </pre>
     *
     * @param values 조합할 복수의 문자열
     * @param delimiter 조합할 복수의 문자열 사이에 추가할 구분자
     * @param useDelimiter 구분자 사용여부
     * @return 조합된 하나의 문자열
     */
    public static String appendAll(String[] values, String delimiter, boolean useDelimiter) {
        StringBuilder sb = new StringBuilder();
        if (useDelimiter && delimiter != null) {
            int arrSize = values.length;
            for (int i = 0; i < arrSize; i++) {
                sb.append(values[i]);
                if ((i + 1) != arrSize) {
                    sb.append(delimiter);
                }
            }
        } else {
            for (String v : values) {
                sb.append(v);
            }
        }
        return sb.toString();
    }

    /**
     * 지정된 지점의 문자열을 지정된 문자로 마스킹하고 반환합니다.
     *
     * @param value 마스킹 할 문자열
     * @param maskChar 마스킹 문자
     * @param start 마스킹 시작지점
     * @param end 마스킹 종료지점
     * @return 마스킹 된 문자열
     */
    public static String masking(String value, char maskChar, int start, int end) {
        if (start > end) throw new IllegalArgumentException("end 값이 start보다 크거나 같을 수 없습니다");
        char[] arr = value.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (i >= start && i <= end) {
                arr[i] = maskChar;
            }
        }
        return new String(arr);
    }

    public static String masking(String value, char maskChar) {
        return masking(value, maskChar, 0, value.length() - 1);
    }

    /**
     * Double 값을 지정된 포맷의 String 값으로 변환하고 반환합니다.
     *
     * @param value 변환할 Double 값
     * @param format 포맷
     * @return 지정된 포맷으로 변환된 String 값
     */
    public static String decimalToString(double value, String format) {
        DecimalFormat df = new DecimalFormat(format);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }

    /**
     * <pre>
     * Double 값을 지정된 포맷의 String 값으로 변환하고 반환합니다.
     * Rounding 모드를 추가로 지정할 수 있습니다. (EX. 올림, 버림, 반올림 등...)
     * Rounding 모드는 [RoundingMode] enum을 참조하십시오.
     * </pre>
     *
     * @param value 변환할 Double 값
     * @param format 포맷
     * @param mode [RoundingMode] enum 참조
     * @return 지정된 포맷으로 변환된 String 값
     */
    public static String decimalToString(double value, String format, RoundingMode mode) {
        DecimalFormat df = new DecimalFormat(format);
        df.setRoundingMode(mode);
        return df.format(value);
    }

    /**
     * Int 값을 지정된 포맷의 String 값으로 변환하고 반환합니다.
     *
     * @param value 변환할 Int 값
     * @param format 포맷
     * @return 지정된 포맷으로 변환된 String 값
     */
    public static String intToString(int value, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(value);
    }

    /**
     * 문자열에 포함된 문자들을 무작위로 섞은 뒤, 반환합니다.
     *
     * @param value 원본 문자열
     * @return 원본 문자열을 무작위로 섞은 문자열
     */
    public static String scrambleChars(@NonNull String value) {
        char[] ca = value.toCharArray();
        List<Character> orgValueMutableList = new ArrayList<>();
        for (char c : ca) {
            orgValueMutableList.add(c);
        }
        char[] newCharArray = new char[ca.length];
        SecureRandom sr = new SecureRandom();

        int i = 0;
        while (i < newCharArray.length) {
            int random = sr.nextInt(orgValueMutableList.size());
            newCharArray[i] = orgValueMutableList.get(random);
            orgValueMutableList.remove(random);
            i++;
        }

        return new String(newCharArray);
    }

    /**
     * 인자로 받은 문자열 집합과 구분자를 순차적으로 조합하고 반환합니다.
     *
     * @param values 조합할 문자열 집합
     * @param separator 구분자
     * @return 조합된 문자열
     */
    public static String combineStrings(@NonNull String[] values, char separator) {
        StringBuilder sb = new StringBuilder();
        for (String v : values) {
            sb.append(v).append(separator);
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static String combineStrings(@NonNull String... values) {
        return combineStrings(values, ' ');
    }

    /**
     * 문자열이 null인지 확인하고 반환합니다.
     * null -> true
     * ""   -> false
     * " "  -> false
     *
     * @param value 확인할 문자열
     * @return 문자열이 null인 경우 true, 아닌 경우 false
     */
    public static boolean isNull(CharSequence value) {
        return value == null;
    }

    /**
     * 문자열이 null이거나 빈 문자열인지 확인하고 반환합니다.
     * null -> true
     * ""   -> true
     * " "  -> false
     *
     * @param value 확인할 문자열
     * @return 문자열이 null혹은 빈 문자열인 경우 true, 아닌 경우 false
     */
    public static boolean isEmpty(CharSequence value) {
        return org.apache.commons.lang3.StringUtils.isEmpty(value);
    }

    /**
     * 문자열이 null, 빈 문자열 혹은 공백 문자열인지 확인하고 반환합니다.
     * null    -> true
     * ""      -> true
     * " "     -> true
     * "     " -> true
     *
     * @param value 확인할 문자열
     * @return 문자열이 null, 빈 문자열 혹은 공백인 경우 true, 아닌 경우 false
     */
    public static boolean isBlank(CharSequence value) {
        return org.apache.commons.lang3.StringUtils.isBlank(value);
    }

    /**
     * 문자열의 앞뒤 공백을 제거하고 반환합니다.
     * \\u0020 이하의 공백들만 제거됩니다.
     * -------------------------------------
     * nullReturnNull이 true인 경우
     * " Hello, World! " -> "Hello, World!"
     * "               " -> ""
     * null              -> null
     * -------------------------------------
     * nullReturnNull이 false인 경우
     * " Hello, World! " -> "Hello, World!"
     * "               " -> ""
     * null              -> ""
     *
     * @param value 공백을 제거할 문자열
     * @param nullReturnNull value가 null로 들어올 경우 null로 반환할지에 대한 여부 (true: null 반환, false: "" 반환)
     * @return 공백이 제거된 문자열
     */
    public static String trim(String value, boolean nullReturnNull) {
        return nullReturnNull ? org.apache.commons.lang3.StringUtils.trim(value) : org.apache.commons.lang3.StringUtils.trimToEmpty(value);
    }

    public static String trim(String value) {
        return trim(value, true);
    }

    /**
     * 문자열의 앞뒤 공백을 제거하고 반환합니다.
     * 유니코드상에 존재하는 모든 공백문자를 제거합니다.
     * -------------------------------------
     * nullReturnNull이 true인 경우
     * " Hello, World! " -> "Hello, World!"
     * "               " -> ""
     * null              -> null
     * -------------------------------------
     * nullReturnNull이 false인 경우
     * " Hello, World! " -> "Hello, World!"
     * "               " -> ""
     * null              -> ""
     *
     * @param value 공백을 제거할 문자열
     * @param nullReturnNull value가 null로 들어올 경우 null로 반환할지에 대한 여부 (true: null 반환, false: "" 반환)
     * @return 공백이 제거된 문자열
     */
    public static String strip(String value, boolean nullReturnNull) {
        return nullReturnNull ? org.apache.commons.lang3.StringUtils.strip(value) : org.apache.commons.lang3.StringUtils.stripToEmpty(value);
    }

    public static String strip(String value) {
        return strip(value, true);
    }
}
