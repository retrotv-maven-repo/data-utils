package dev.retrotv.data.utils

object PasswordUtils {

    /**
     * 패스워드 길이를 확인합니다.
     *
     * @param password 길이를 검증할 패스워드 문자열
     * @param minLength 최소 길이 (기본 값: 8)
     * @param maxLength 최대 길이 (기본 값: 16)
     * @return 패스워드의 길이 만족 여부
     */
    @JvmStatic
    @JvmOverloads
    fun checkLength(password: CharSequence, minLength: Int = 8, maxLength: Int = 16): Boolean {
        return password.length in minLength..maxLength
    }

    /**
     * 패스워드에 특정 문자열(대문자, 소문자, 숫자, 특수문자)이 존재하는지 확인합니다.
     *
     * @param password
     * @param includeLowerCaseEnglish 소문자 포함여부 확인
     * @param includeUpperCaseEnglish 대문자 포함여부 확인
     * @param includeNumber 숫자 포함여부 확인
     * @param includeSpecialCharacter 특수문자 포함여부 확인
     */
    @JvmStatic
    @JvmOverloads
    fun isInclude(
        password: CharSequence,
        includeLowerCaseEnglish: Boolean = true,
        includeUpperCaseEnglish: Boolean = true,
        includeNumber: Boolean = true,
        includeSpecialCharacter: Boolean = true
    ): Boolean {
        if (includeLowerCaseEnglish && !ValidUtils.isIncludeLowerCase(password.toString())) {
            return false
        }

        if (includeUpperCaseEnglish && !ValidUtils.isIncludeUpperCase(password.toString())) {
            return false
        }

        if (includeNumber && !ValidUtils.isIncludeNumber(password.toString())) {
            return false
        }

        if (includeSpecialCharacter && !ValidUtils.isIncludeSpecialCharacter(password.toString())) {
            return false
        }

        return true
    }
}
