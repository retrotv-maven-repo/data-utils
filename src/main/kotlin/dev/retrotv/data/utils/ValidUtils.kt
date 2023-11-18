@file:JvmName("ValidUtils")
package dev.retrotv.data.utils

/**
 * 영문자 포함 여부를 반환합니다.
 *
 * @param value 검증할 문자열
 * @return 영문자 포함 여부
 */
fun isIncludeEnglish(value: String): Boolean {
    val re = Regex(".*[a-zA-Z]+.*")
    return value.matches(re)
}

/**
 * 영소문자 포함 여부를 반환합니다.
 *
 * @param value 검증할 문자열
 * @return 영소문자 포함 여부
 */
fun isIncludeLowerCase(value: String): Boolean {
    val re = Regex(".*[a-z]+.*")
    return value.matches(re)
}

/**
 * 영대문자 포함 여부를 반환합니다.
 *
 * @param value 검증할 문자열
 * @return 영대문자 포함 여부
 */
fun isIncludeUpperCase(value: String): Boolean {
    val re = Regex(".*[A-Z]+.*")
    return value.matches(re)
}

/**
 * 숫자 포함 여부를 반환합니다.
 *
 * @param value 검증할 문자열
 * @return 숫자 포함 여부
 */
fun isIncludeNumber(value: String): Boolean {
    val re = Regex(".*[0-9]+.*")
    return value.matches(re)
}

/**
 * 특수문자 포함 여부를 반환합니다.
 *
 * @param value 검증할 문자열
 * @return 특수문자 포함 여부
 */
fun isIncludeSpecialCharacter(value: String): Boolean {
    val re = Regex(".*[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣\\s]+.*")
    return value.matches(re)
}

/**
 * 한글 포함 여부를 반환합니다.
 *
 * @param value 검증할 문자열
 * @return 한글 포함 여부
 */
fun isIncludeKorean(value: String): Boolean {
    val re = Regex(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")
    return value.matches(re)
}

/**
 * 유효한 날짜(yyyyMMdd 혹은 yyyy-MM-dd)인지 검증 여부를 반환합니다.
 *
 * @param value 검증할 문자열
 * @return 유효한 날짜인지 검증 여부
 */
fun isDate(value: String): Boolean {
    if (value.length != 8 && value.length != 10) {
        return false
    }

    if (value.length == 8 && !value.matches(Regex("^\\d{8}$"))) {
        return false
    }

    if (value.length == 10 && !value.matches(Regex("^\\d{4}-\\d{2}-\\d{2}$"))) {
        return false
    }

    val newValue = value.replace("-", "")

    val year = newValue.substring(0, 4).toInt()
    val month = newValue.substring(4, 6).toInt()
    val day = newValue.substring(6, 8).toInt()

    if (year in 0..9999 && month in 1..12) {
        when (month) {
            1, 3, 5, 7, 8, 10, 12 ->
                return day in 1..31
            4, 6, 9, 11 ->
                return day in 1..30
            2 ->
                return if (isLeapYear(year)) { day in 1..29 } else { day in 1..28 }
        }
    }

    return false
}

/**
 * 이메일 형식인지 확인합니다.
 *
 * @param value 검증할 문자열
 * @return 이메일 형식 여부
 */
fun isEmail(value: String): Boolean {
    val re = Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")
    return re.matches(value)
}

/**
 * 유선 전화번호 형식인지 확인합니다.
 *
 * @param value 검증할 문자열
 * @return 유선 전화번호 형식 여부
 */
fun isHomePhoneNumber(value: String): Boolean {

    // 서울
    return if (value.substring(0 until 2) == "02") {
        val cuttingNum = value.substring(2)
        val re = Regex("^([-)])?\\d{3,4}-?\\d{4}\$")
        re.matches(cuttingNum)

        // 서울 외
    } else {

        // 지역 번호
        val areaNum = value.substring(0 until 3)
        var re = Regex("^0(31|32|33|41|42|43|44|51|52|53|54|55|61|62|63|64)\$")

        // 허용 된 지역 번호
        return if (re.matches(areaNum)) {
            val cuttingNum = value.substring(3)
            re = Regex("^([-)])?\\d{3,4}-?\\d{4}\$")
            re.matches(cuttingNum)

            // 그 외
        } else { false }
    }
}

/**
 * 휴대 전화번호 형식인지 확인합니다.
 *
 * @param value 검증할 문자열
 * @return 휴대 전화번호 형식 여부
 */
fun isCellPhoneNumber(value: String): Boolean {
    val re = Regex("^01([016789])([-)])?\\d{3,4}-?\\d{4}\$")
    return re.matches(value)
}

/**
 * 유선 전화번호 형식 혹은 휴대 전화번호 형식인지 확인합니다.
 *
 * @param value 검증할 문자열
 * @return 유선/휴대 전화번호 형식 여부
 */
fun isPhoneNumber(value: String): Boolean {
    val result1 = isHomePhoneNumber(value)
    val result2 = isCellPhoneNumber(value)
    return result1 || result2
}

/**
 * 인수로 넘어온 객체가 null인지 확인합니다.
 *
 * @para obj 검증할 객체
 * @return null 여부
 */
fun isNull(obj: Any?): Boolean {
    return obj == null
}
