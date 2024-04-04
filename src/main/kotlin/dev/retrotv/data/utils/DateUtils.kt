package dev.retrotv.data.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar

object DateUtils {

    /**
     * 윤년 여부를 반환합니다.
     *
     * @param year 연도
     * @return 윤년 여부
     */
    @JvmStatic
    fun isLeapYear(year: String): Boolean {
        require(isValidYear(year)) { NOT_VALID_YEAR }
        return isLeapYear(year.toInt())
    }

    /**
     * 윤년 여부를 반환합니다.
     *
     * @param year 연도
     * @return 윤년 여부
     */
    @JvmStatic
    fun isLeapYear(year: Int): Boolean {
        require(isValidYear(year)) { NOT_VALID_YEAR }
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
    }

    /**
     * <pre>
     * 해당하는 달의 마지막 날을 구합니다.
     * year는 필수 인수가 아니지만, month가 2일 경우에는 필수 입니다.
     * </pre>
     *
     * @param year 연도
     * @param month 달
     * @return month에 해당하는 달의 마지막 날
     */
    @JvmStatic
    fun getLastDay(year: String?, month: String): String {
        require(year == null || year.length == 4) { NOT_NULL_OR_VALID_YEAR }
        require(isValidMonth(month)) { NOT_VALID_MONTH }
        return getLastDay(year?.toInt(), month.toInt()).toString()
    }

    /**
     * <pre>
     * 해당하는 달의 마지막 날을 구합니다.
     * year는 필수 인수가 아니지만, month가 2일 경우에는 필수입니다.
     * </pre>
     *
     * @param year 연도
     * @param month 달
     * @return month에 해당하는 달의 마지막 날
     */
    @JvmStatic
    fun getLastDay(year: Int?, month: Int): Int {
        require(year == null || isValidYear(year)) { NOT_NULL_OR_VALID_YEAR }
        require(isValidMonth(month)) { NOT_VALID_MONTH }
        require(!(month == 2 && year == null)) { "2월달의 마지막 날을 가져오기 위해서는 year 인수가 필수입니다." }

        return when (month) {
            1, 3, 5, 7, 8, 10, 12 ->
                31
            4, 6, 9, 11 ->
                30
            2 ->
                if (isLeapYear(year!!)) { 29 } else { 28 }
            else ->
                throw IllegalArgumentException(NOT_VALID_MONTH)
        }
    }

    /**
     * 유효한 일자인지 확인합니다.
     *
     * @param date yyyyMMdd 혹은 yyyy-MM-dd 형식의 문자열
     * @return 유효 여부
     */
    @JvmStatic
    fun isValidDate(date: String): Boolean {
        require(date.length == 8 || date.length == 10) {
            "date는 8 혹은 10글자의 문자열만 허용됩니다."
        }

        val replacedDate = date.replace("-", "")
        require(replacedDate.length == 8) {
            "date는 숫자 혹은 '-'로만 구성되어 있어야 합니다."
        }

        val year = replacedDate.substring(0, 4).toInt()
        val month = replacedDate.substring(4, 6).toInt()
        val day = replacedDate.substring(6, 8).toInt()

        return isValidYear(year) && isValidMonth(month) && isValidDay(year, month, day)
    }

    /**
     * <pre>
     * 입력받은 date 인수에 year, month, day 인수를 합산 한 뒤 반환 합니다.
     * date 인수의 포맷 유형과 format의 포맷 유형이 다를경우, 정상적인 합산을 보장하지 않습니다.
     *
     * addYMD("20231231", 0, 0, 1, "yyyyMMdd") => 결과: "20240101"
     * addYMD("20240131", 0, 1, 0, "yyyyMMdd") => 결과: "20240229"
     * addYMD("20231231", 1, 0, 0, "yyyyMMdd") => 결과: "20241231"
     * addYMD("2023-12-31", 0, 0, 1, "yyyy-MM-dd") => 결과: "2024-01-01"
     * addYMD("2024-01-31", 0, 1, 0, "yyyy-MM-dd") => 결과: "2024-02-29"
     * addYMD("2023-12-31", 1, 0, 0, "yyyy-MM-dd") => 결과: "2024-12-31"
     * </pre>
     *
     * @param date 기준 일자
     * @param year 합산할 연
     * @param month 합산할 월
     * @param day 합산할 일
     * @param format date 인수의 포맷 유형
     * @return date 인수에 year, month, day를 모두 합산하고 난 뒤의 문자열로 이루어진 일자
     * @exception ParseException 지원되지 않는 유형의 format일 경우 던져짐
     */
    @JvmStatic
    @Throws(ParseException::class)
    fun addYMD(date: String, year: Int?, month: Int?, day: Int?, format: String): String {
        require(isValidDate(date)) { NOT_VALID_DATE }
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat(format)

        cal.time = sdf.parse(date)
        if (year != null && year != 0) { cal.add(Calendar.YEAR, year) }
        if (month != null && month != 0) { cal.add(Calendar.MONTH, month) }
        if (day != null && day != 0) { cal.add(Calendar.DATE, day) }

        return sdf.format(cal.time)
    }
}

private const val NOT_VALID_DATE = "date가 유효한 일자가 아닙니다."
private const val NOT_VALID_YEAR = "year가 유효한 연도가 아닙니다."
private const val NOT_NULL_OR_VALID_YEAR = "year가 null이 아니면서, 유효한 연도가 아닙니다."
private const val NOT_VALID_MONTH = "month가 유효한 월이 아닙니다."

private fun isValidYear(year: String): Boolean = year.toInt() in 1..9999

private fun isValidYear(year: Int): Boolean = year in 1..9999

private fun isValidMonth(month: String): Boolean = month.toInt() in 1..12

private fun isValidMonth(month: Int): Boolean = month in 1..12

private fun isValidDay(year: String, month: String, day: String): Boolean =
    day.toInt() in 1..DateUtils.getLastDay(year, month).toInt()

private fun isValidDay(year: Int, month: Int, day: Int): Boolean = day in 1..DateUtils.getLastDay(year, month)
