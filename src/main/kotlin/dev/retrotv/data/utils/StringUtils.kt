@file:JvmName("StringUtils")
package dev.retrotv.data.utils

import java.lang.IllegalArgumentException
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * <pre>
 * 문자열 끝에 개행문자를 추가하고 반환합니다.
 * Windows 계열의 운영체제인 경우 "\r\n"을, 그 외의 운영체제는 "\n"을 문자열 끝에 추가합니다.
 * </pre>
 *
 * @param value 개행문자를 추가할 문자열
 * @return 개행문자가 추가된 문자열
 */
fun addNewLine(value: String): String {
    val os = System.getProperty("os.name").lowercase()
    return if (os.contains("win")) {
        value + "\r\n"
    } else {
        value + "\n"
    }
}

/**
 * <pre>
 * 문자열에 포함된 개행문자("\r", "\n")를 모두 제거하고 반환합니다.
 * </pre>
 *
 * @param value 개행문자를 제거할 문자열
 * @return 개행문자가 제거된 문자열
 */
fun removeNewLine(value: String): String = value.replace("\r", "")
                                                .replace("\n", "")

/**
 * <pre>
 * 인자로 받은 모든 문자열을 순차적으로 조합한 뒤, 반환합니다.
 *
 * <b>- Java</b>
 * StringUtils.appendAll("Hello", "World");
 * 혹은
 * StringUtils.appendAll({ "Hello", "World" })
 *
 * <b>- Kotlin</b>
 * appendAll("Hello", "World")
 * 혹은
 * appendAll(arrayOf("Hello", "World"))
 * </pre>
 *
 * @param values 조합할 복수의 문자열
 * @return 조합된 하나의 문자열
 */
fun appendAll(vararg values: String): String {
    val sb: StringBuilder = StringBuilder()
    values.forEach { v -> sb.append(v) }

    return sb.toString()
}

/**
 * <pre>
 * 인자로 받은 모든 문자열 사이에 구분자와 함께 순차적으로 조합한 뒤, 반환합니다.
 * values 인자로 받은 마지막 문자열 끝에는 구분자가 조합되지 않습니다.
 * 
 * <b>- Java</b>
 * StringUtils.appendAll({ "Hello", "World" }, " ", true);
 * 결과
 * "Hello World"
 * 
 * <b>- Kotlin</b>
 * appendAll("Hello", "World", delimiter = " ", useDelimiter = true)
 * 혹은
 * appendAll(values = arrayOf("Hello", "World"), delimiter = " ", useDelimiter = true)
 * 결과
 * "Hello World"
 * </pre>
 *
 * @param values 조합할 복수의 문자열
 * @param delimiter 조합할 복수의 문자열 사이에 추가할 구분자
 * @param useDelimiter 구분자 사용여부
 * @return 조합된 하나의 문자열
 */
fun appendAll(vararg values: String, delimiter: String? = null, useDelimiter: Boolean = false): String {
    val sb: StringBuilder = StringBuilder()
    if (useDelimiter && delimiter != null) {
        val arrSize = values.size
        values.forEachIndexed { i, v ->
            sb.append(v)
            if ((i + 1) != arrSize) {
                sb.append(delimiter)
            }
        }
    } else {
        values.forEach { v -> sb.append(v) }
    }

    return sb.toString()
}

/**
 * <pre>
 * 지정된 지점의 문자열을 지정된 문자로 마스킹하고 반환합니다.
 * </pre>
 *
 * @param value 마스킹 할 문자열
 * @param maskChar 마스킹 문자
 * @param start 마스킹 시작지점
 * @param end 마스킹 종료지점
 * @return 마스킹 된 문자열
 */
fun masking(value: String,
            maskChar: Char,
            start: Int = 0,
            end: Int = value.toCharArray().size - 1): String {
    require(start <= end) {
        throw IllegalArgumentException("end 값이 start보다 크거나 같을 수 없습니다")
    }

    val arr = value.toCharArray()
    for (i in arr.indices) {
        if (i in start..end) {
            arr[i] = maskChar
        }
    }

    return String(arr)
}

/**
 * <pre>
 * Double 값을 지정된 포맷의 String 값으로 변환하고 반환합니다.
 * </pre>
 *
 * @param value 변환할 Double 값
 * @param format 포맷
 * @return 지정된 포맷으로 변환된 String 값
 */
fun deciamlToString(value: Double, format: String): String {
    val df = DecimalFormat(format)
    df.roundingMode = RoundingMode.HALF_UP
    return df.format(value)
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
fun deciamlToString(value: Double, format: String, mode: RoundingMode): String {
    val df = DecimalFormat(format)
    df.roundingMode = mode
    return df.format(value)
}

/**
 * <pre>
 * Int 값을 지정된 포맷의 String 값으로 변환하고 반환합니다.
 * </pre>
 *
 * @param value 변환할 Int 값
 * @param format 포맷
 * @return 지정된 포맷으로 변환된 String 값
 */
fun intToString(value: Int, format: String): String {
    val df = DecimalFormat(format)
    return df.format(value)
}

fun isIncludeEnglish(value: String): Boolean {
    val re = Regex(".*[a-zA-Z]+.*")
    return value.matches(re)
}

fun isIncludeLowerCaseEnglish(value: String): Boolean {
    val re = Regex(".*[a-z]+.*")
    return value.matches(re)
}

fun isIncludeUpperCaseEnglish(value: String): Boolean {
    val re = Regex(".*[A-Z]+.*")
    return value.matches(re)
}

fun isIncludeSpecialCharacter(value: String): Boolean {
    val re = Regex(".*[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣\\s]+.*")
    return value.matches(re)
}

fun isIncludeKorean(value: String): Boolean {
    val re = Regex(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")
    return value.matches(re)
}

/**
 * <pre>
 * 이메일 형식인지 확인합니다.
 * </pre>
 *
 * @param value 확인할 값
 * @return 이메일 형식 여부
 */
fun isEmail(value: String): Boolean {
    val re = Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")
    return re.matches(value)
}

/**
 * <pre>
 * 유선 전화번호 형식인지 확인합니다.
 * </pre>
 *
 * @param value 확인할 값
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
 * <pre>
 * 휴대 전화번호 형식인지 확인합니다.
 * </pre>
 *
 * @param value 확인할 값
 * @return 휴대 전화번호 형식 여부
 */
fun isCellPhoneNumber(value: String): Boolean {
    val re = Regex("^01([016789])([-)])?\\d{3,4}-?\\d{4}\$")
    return re.matches(value)
}

/**
 * <pre>
 * 유선 전화번호 형식 혹은 휴대 전화번호 형식인지 확인합니다.
 * </pre>
 *
 * @param value 확인할 값
 * @return 유선/휴대 전화번호 형식 여부
 */
fun isPhoneNumber(value: String): Boolean {
    val result1 = isHomePhoneNumber(value)
    val result2 = isCellPhoneNumber(value)
    return result1 || result2
}
