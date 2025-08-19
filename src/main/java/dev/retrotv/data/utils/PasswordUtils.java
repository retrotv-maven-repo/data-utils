package dev.retrotv.data.utils;

import lombok.NonNull;

/**
 * 패스워드 관련 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 패스워드의 길이와 포함된 문자 유형을 검증하는 메서드를 제공합니다.
 * 패스워드의 길이를 최소 및 최대 길이로 검증하거나, 특정 문자 유형(대문자, 소문자, 숫자, 특수문자)의 포함 여부를 확인할 수 있습니다.
 * </p>
 *
 * @author  yjj8353
 * @since   1.0.0
 */
public final class PasswordUtils {

    private PasswordUtils() {
        throw new UnsupportedOperationException("PasswordUtils는 인스턴스화 할 수 없습니다.");
    }

    /**
     * 패스워드 길이를 확인합니다.
     * 최소 길이와 최대 길이를 모두 검증합니다.
     *
     * @param password 길이를 검증할 패스워드 문자열
     * @param minLength 최소 길이
     * @param maxLength 최대 길이
     * @return 패스워드의 길이 만족 여부
     */
    public static boolean checkLength(@NonNull CharSequence password, int minLength, int maxLength) {
        return password.length() >= minLength && password.length() <= maxLength;
    }

    /**
     * 패스워드 길이를 확인합니다.
     * 최소 길이만 검증하며, 최대 길이는 무시합니다.
     *
     * @param password 길이를 검증할 패스워드 문자열
     * @param minLength 최소 길이
     * @return 패스워드의 길이 만족 여부
     */
    public static boolean checkLength(@NonNull CharSequence password, int minLength) {
        return password.length() >= minLength;
    }

    /**
     * 패스워드 길이를 확인합니다.
     * 최소 8자, 최대 16자 길이를 만족하는지 검증합니다.
     *
     * @param password 길이를 검증할 패스워드 문자열
     * @return 패스워드의 길이 만족 여부
     */
    public static boolean checkLength(@NonNull CharSequence password) {
        return checkLength(password, 8, 16);
    }

    /**
     * 패스워드에 특정 문자열(대문자, 소문자, 숫자, 특수문자)이 존재하는지 확인합니다.
     *
     * @param password 검증할 패스워드 문자열
     * @param includeLowerCaseEnglish 소문자 포함여부 확인
     * @param includeUpperCaseEnglish 대문자 포함여부 확인
     * @param includeNumber 숫자 포함여부 확인
     * @param includeSpecialCharacter 특수문자 포함여부 확인
     */
    public static boolean isInclude(
        @NonNull CharSequence password,
        boolean includeLowerCaseEnglish,
        boolean includeUpperCaseEnglish,
        boolean includeNumber,
        boolean includeSpecialCharacter
    ) {
        String pw = password.toString();
        if (includeLowerCaseEnglish && !ValidUtils.isIncludeLowerCase(pw)) {
            return false;
        }
        if (includeUpperCaseEnglish && !ValidUtils.isIncludeUpperCase(pw)) {
            return false;
        }
        if (includeNumber && !ValidUtils.isIncludeNumber(pw)) {
            return false;
        }

        return !includeSpecialCharacter || ValidUtils.isIncludeSpecialCharacter(pw);
    }

    /**
     * 패스워드에 대문자, 소문자, 숫자, 특수문자가 모두 존재하는지 확인합니다.
     *
     * @param password 검증할 패스워드 문자열
     */
    public static boolean isInclude(@NonNull CharSequence password) {
        return isInclude(password, true, true, true, true);
    }
}