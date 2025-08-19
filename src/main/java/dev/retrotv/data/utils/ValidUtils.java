package dev.retrotv.data.utils;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Pattern;

/**
 * 문자열 검증 관련 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 문자열이 특정 조건을 만족하는지 검증하는 메서드를 제공합니다.
 * 예를 들어, 영문자, 숫자, 특수문자 포함 여부, 날짜 형식, 이메일 형식 등을 확인할 수 있습니다.
 * </p>
 *
 * @author yjj8353
 * @since 1.0.0
 */
public final class ValidUtils {
    private ValidUtils() {
        throw new UnsupportedOperationException("ValidUtils는 인스턴스화 할 수 없습니다.");
    }

    /**
     * 영문자 포함 여부를 반환합니다.
     *
     * @param value 검증할 문자열
     * @return 영문자 포함 여부
     */
    public static boolean isIncludeEnglish(String value) {
        return Pattern.matches(".*[a-zA-Z]+.*", value);
    }

    /**
     * 영소문자 포함 여부를 반환합니다.
     *
     * @param value 검증할 문자열
     * @return 영소문자 포함 여부
     */
    public static boolean isIncludeLowerCase(String value) {
        return Pattern.matches(".*[a-z]+.*", value);
    }

    /**
     * 영대문자 포함 여부를 반환합니다.
     *
     * @param value 검증할 문자열
     * @return 영대문자 포함 여부
     */
    public static boolean isIncludeUpperCase(String value) {
        return Pattern.matches(".*[A-Z]+.*", value);
    }

    /**
     * 숫자 포함 여부를 반환합니다.
     *
     * @param value 검증할 문자열
     * @return 숫자 포함 여부
     */
    public static boolean isIncludeNumber(String value) {
        return Pattern.matches(".*\\d+.*", value);
    }

    /**
     * 특수문자 포함 여부를 반환합니다.
     *
     * @param value 검증할 문자열
     * @return 특수문자 포함 여부
     */
    public static boolean isIncludeSpecialCharacter(String value) {
        return Pattern.matches(".*[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣\\s]+.*", value);
    }

    /**
     * 한글 포함 여부를 반환합니다.
     *
     * @param value 검증할 문자열
     * @return 한글 포함 여부
     */
    public static boolean isIncludeKorean(String value) {
        return Pattern.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*", value);
    }

    /**
     * 유효한 날짜(yyyyMMdd 혹은 yyyy-MM-dd)인지 검증 여부를 반환합니다.
     *
     * @param value 검증할 문자열
     * @return 유효한 날짜인지 검증 여부
     */
    public static boolean isDate(String value) {
        if (value.length() != 8 && value.length() != 10) {
            return false;
        }

        if (value.length() == 8 && !Pattern.matches("^\\d{8}$", value)) {
            return false;
        }

        if (value.length() == 10 && !Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", value)) {
            return false;
        }

        String newValue = value.replace("-", "");
        int year = Integer.parseInt(newValue.substring(0, 4));
        int month = Integer.parseInt(newValue.substring(4, 6));
        int day = Integer.parseInt(newValue.substring(6, 8));

        if (year >= 0 && year <= 9999 && month >= 1 && month <= 12) {
            switch (month) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    return day >= 1 && day <= 31;
                case 4: case 6: case 9: case 11:
                    return day >= 1 && day <= 30;
                case 2:
                    return DateUtils.isLeapYear(year) ? (day >= 1 && day <= 29) : (day >= 1 && day <= 28);
                default:
                    throw new IllegalArgumentException("잘못된 월: " + month);
            }
        }

        return false;
    }

    /**
     * 이메일 형식인지 확인합니다.
     *
     * @param value 검증할 문자열
     * @return 이메일 형식 여부
     */
    public static boolean isEmail(String value) {
        return EmailValidator.getInstance().isValid(value);
    }

    /**
     * 유선 전화번호 형식인지 확인합니다.
     *
     * @param value 검증할 문자열
     * @return 유선 전화번호 형식 여부
     */
    public static boolean isHomePhoneNumber(String value) {
        if (value.length() < 2) return false;

        // 서울
        if (value.startsWith("02")) {
            String cuttingNum = value.substring(2);
            return Pattern.matches("^([-)])?\\d{3,4}-?\\d{4}$", cuttingNum);
        } else if (value.length() >= 3) {
            // 지역 번호
            String areaNum = value.substring(0, 3);
            boolean isValidArea = Pattern.matches("^0(31|32|33|41|42|43|44|51|52|53|54|55|61|62|63|64)$", areaNum);
            if (isValidArea) {
                String cuttingNum = value.substring(3);
                return Pattern.matches("^([-)])?\\d{3,4}-?\\d{4}$", cuttingNum);
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 휴대 전화번호 형식인지 확인합니다.
     *
     * @param value 검증할 문자열
     * @return 휴대 전화번호 형식 여부
     */
    public static boolean isCellPhoneNumber(String value) {
        return Pattern.matches("^01([016789])([-)])?\\d{3,4}-?\\d{4}$", value);
    }

    /**
     * 유선 전화번호 형식 혹은 휴대 전화번호 형식인지 확인합니다.
     *
     * @param value 검증할 문자열
     * @return 유선/휴대 전화번호 형식 여부
     */
    public static boolean isPhoneNumber(String value) {
        return isHomePhoneNumber(value) || isCellPhoneNumber(value);
    }

    /**
     * 인수로 넘어온 객체가 null인지 확인합니다.
     *
     * @param obj 검증할 객체
     * @return null 여부
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }
}