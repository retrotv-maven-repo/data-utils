@file:JvmName("DateUtils")
package dev.retrotv.data.utils

/**
 * 윤년 여부를 반환합니다.
 *
 * @param year 연도
 * @return 윤년 여부
 */
fun isLeapYear(year: String): Boolean {
    return isLeapYear(year.toInt())
}

/**
 * 윤년 여부를 반환합니다.
 *
 * @param year 연도
 * @return 윤년 여부
 */
fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
}

/**
 * 해당하는 달의 마지막 날을 구합니다.
 * year는 필수 인수가 아니지만, month가 2일 경우에는 필수입니다.
 *
 * @param year 연도
 * @param month 달
 * @return month에 해당하는 달의 마지막 날
 */
fun getLastDay(year: String?, month: String): String {
    require(year == null || year.length == 4) { "year는 null 혹은 4자리 숫자로 구성되어야 합니다." }
    require(month.length in 1..2) { "month는 1~2자리 숫자로 구성되어야 합니다." }
    return getLastDay(year?.toInt(), month.toInt()).toString()
}

/**
 * 해당하는 달의 마지막 날을 구합니다.
 * year는 필수 인수가 아니지만, month가 2일 경우에는 필수입니다.
 *
 * @param year 연도
 * @param month 달
 * @return month에 해당하는 달의 마지막 날
 */
fun getLastDay(year: Int?, month: Int): Int {
    require(year == null || year in 0..9999) { "year는 null 이거나 혹은 0부터 9999까지의 숫자만 허용됩니다." }
    require(month in 1..12) { "month는 1부터 12까지의 숫자만 허용됩니다." }
    require(!(month == 2 && year == null)) { "2월달의 마지막 날을 가져오기 위해서는 year 인수가 필수입니다." }

    return when (month) {
        1, 3, 5, 7, 8, 10, 12 ->
            31
        4, 6, 9, 11 ->
            30
        2 ->
            if (isLeapYear(year!!)) { 29 } else { 28 }
        else ->
            throw IllegalArgumentException("month는 1부터 12까지의 숫자만 허용됩니다.")
    }
}
