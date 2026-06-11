package dev.retrotv.data.utils;

import lombok.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 날짜 관련 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 날짜와 관련된 다양한 유틸리티 메서드를 제공합니다. 윤년 여부 확인, 특정 달의 마지막 날 계산,
 * 날짜 문자열의 유효성 검사, 날짜 문자열과 Date/LocalDate/LocalDateTime 간의 변환 등을 지원합니다.
 * </p>
 *
 * @author yjj8353
 * @since 1.0.0
 */
public final class DateUtils {
    private static final String NOT_VALID_DATE = "date가 유효한 일자가 아닙니다.";
    private static final String NOT_VALID_YEAR = "year가 유효한 연도가 아닙니다.";
    private static final String NOT_NULL_OR_VALID_YEAR = "year가 null이 아니면서, 유효한 연도가 아닙니다.";
    private static final String NOT_VALID_MONTH = "month가 유효한 월이 아닙니다.";
    private static final String DEFAULT_FORMAT = "yyyyMMdd";

    private DateUtils() {
        throw new UnsupportedOperationException("DateUtils는 인스턴스를 생성할 수 없습니다.");
    }

    /**
     * 윤년 여부를 반환합니다.
     *
     * @param year 연도
     * @return 윤년 여부
     */
    public static boolean isLeapYear(@NonNull String year) {
        if (!isValidYear(year)) {
            throw new IllegalArgumentException(NOT_VALID_YEAR);
        }

        return isLeapYear(Integer.parseInt(year));
    }

    /**
     * 윤년 여부를 반환합니다.
     *
     * @param year 연도
     * @return 윤년 여부
     */
    public static boolean isLeapYear(int year) {
        if (!isValidYear(year)) {
            throw new IllegalArgumentException(NOT_VALID_YEAR);
        }

        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
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
    public static String getLastDay(String year, String month) {
        if (year != null && year.length() != 4) {
            throw new IllegalArgumentException(NOT_NULL_OR_VALID_YEAR);
        }

        if (!isValidMonth(month)) {
            throw new IllegalArgumentException(NOT_VALID_MONTH);
        }

        return Integer.toString(getLastDay(year != null ? Integer.parseInt(year) : null, Integer.parseInt(month)));
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
    public static int getLastDay(Integer year, int month) {
        if (year != null && !isValidYear(year)) {
            throw new IllegalArgumentException(NOT_NULL_OR_VALID_YEAR);
        }

        if (!isValidMonth(month)) {
            throw new IllegalArgumentException(NOT_VALID_MONTH);
        }

        if (month == 2 && year == null) {
            throw new IllegalArgumentException("2월달의 마지막 날을 가져오기 위해서는 year 인수가 필수입니다.");
        }

        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                throw new IllegalArgumentException(NOT_VALID_MONTH);
        }
    }

    /**
     * 유효한 일자인지 확인합니다.
     *
     * @param date yyyyMMdd 혹은 yyyy-MM-dd 형식의 문자열
     * @return 유효 여부
     */
    public static boolean isValidDate(@NonNull String date) {
        if (date.length() != 8 && date.length() != 10) {
            throw new IllegalArgumentException("date는 yyyyMMdd 혹은 yyyy-MM-dd 형식의 문자열만 허용됩니다.");
        }

        String replacedDate = date.replace("-", "");
        if (replacedDate.length() != 8) {
            throw new IllegalArgumentException("date는 숫자 혹은 '-'로만 구성되어 있어야 합니다.");
        }

        try {
            int year = Integer.parseInt(replacedDate.substring(0, 4));
            int month = Integer.parseInt(replacedDate.substring(4, 6));
            int day = Integer.parseInt(replacedDate.substring(6, 8));

            return isValidYear(year) && isValidMonth(month) && isValidDay(year, month, day);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("date는 숫자로만 구성되어야 합니다.");
        }
    }

    /**
     * <pre>
     * 입력받은 date 인수에 year, month, day 인수를 합산 한 뒤 반환 합니다.
     * date 인수의 포맷 유형과 format의 포맷 유형이 다를경우, 정상적인 합산을 보장하지 않습니다.
     *
     * {@code addYMD("20231231", 0, 0, 1, "yyyyMMdd") => 결과: "20240101"}
     * {@code addYMD("20240131", 0, 1, 0, "yyyyMMdd") => 결과: "20240229"}
     * {@code addYMD("20231231", 1, 0, 0, "yyyyMMdd") => 결과: "20241231"}
     * {@code addYMD("2023-12-31", 0, 0, 1, "yyyy-MM-dd") => 결과: "2024-01-01"}
     * {@code addYMD("2024-01-31", 0, 1, 0, "yyyy-MM-dd") => 결과: "2024-02-29"}
     * {@code addYMD("2023-12-31", 1, 0, 0, "yyyy-MM-dd") => 결과: "2024-12-31"}
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
    public static String addYMD(@NonNull String date, Integer year, Integer month, Integer day, String format) throws ParseException {
        if (!isValidDate(date)) {
            throw new IllegalArgumentException(NOT_VALID_DATE);
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        cal.setTime(sdf.parse(date));
        if (year != null && year != 0) {
            cal.add(Calendar.YEAR, year);
        }

        if (month != null && month != 0) {
            cal.add(Calendar.MONTH, month);
        }

        if (day != null && day != 0) {
            cal.add(Calendar.DATE, day);
        }

        return sdf.format(cal.getTime());
    }

    /**
     * date 문자열을 Date 객체로 변환하여 반환합니다. date 문자열은 yyyyMMdd 형식이어야 합니다.
     *
     * @param date 변환할 date 문자열
     * @return 변환된 Date 객체
     * @throws ParseException 지원되지 않는 형식의 date 문자열일 경우 발생
     */
    public static Date stringToDate(@NonNull String date) throws ParseException {
        return stringToDate(date, DEFAULT_FORMAT);
    }

    /**
     * <pre>
     * date 문자열을 Date 객체로 변환하여 반환합니다.
     * format 인수를 통해 date 문자열의 형식을 지정할 수 있습니다. format이 지정되지 않은 경우, 기본적으로 yyyyMMdd 형식이 사용됩니다.
     * </pre>
     *
     * @param date 변환할 date 문자열
     * @param format date 문자열의 형식
     * @return 변환된 Date 객체
     * @throws ParseException 지원되지 않는 형식의 date 문자열일 경우 발생
     */
    public static Date stringToDate(@NonNull String date, String format) throws ParseException {
        if (format == null || format.isEmpty()) {
            format = DEFAULT_FORMAT;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(date);
    }

    /**
     * date 문자열을 LocalDate 객체로 변환하여 반환합니다. date 문자열은 yyyyMMdd 형식이어야 합니다.
     *
     * @param date 변환할 date 문자열
     * @return 변환된 LocalDate 객체
     */
    public static LocalDate stringToLocalDate(@NonNull String date) {
        return stringToLocalDate(date, DEFAULT_FORMAT);
    }

    /**
     * <pre>
     * date 문자열을 LocalDate 객체로 변환하여 반환합니다.
     * format 인수를 통해 date 문자열의 형식을 지정할 수 있습니다. format이 지정되지 않은 경우, 기본적으로 yyyyMMdd 형식이 사용됩니다.
     * </pre>
     *
     * @param date 변환할 date 문자열
     * @param format date 문자열의 형식
     * @return 변환된 LocalDate 객체
     */
    public static LocalDate stringToLocalDate(@NonNull String date, String format) {
        if (format == null || format.isEmpty()) {
            format = DEFAULT_FORMAT;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, formatter);
    }

    /**
     * date 문자열을 LocalDateTime 객체로 변환하여 반환합니다. date 문자열은 yyyyMMdd HH:mm:ss 형식이어야 합니다.
     *
     * @param date 변환할 date 문자열
     * @return 변환된 LocalDateTime 객체
     */
    public static LocalDateTime stringToLocalDateTime(@NonNull String date) {
        return stringToLocalDateTime(date, null);
    }

    /**
     * <pre>
     * date 문자열을 LocalDateTime 객체로 변환하여 반환합니다.
     * format 인수를 통해 date 문자열의 형식을 지정할 수 있습니다. format이 지정되지 않은 경우, 기본적으로 yyyyMMdd HH:mm:ss 형식이 사용됩니다.
     * </pre>
     *
     * @param date 변환할 date 문자열
     * @param format date 문자열의 형식
     * @return 변환된 LocalDateTime 객체
     */
    public static LocalDateTime stringToLocalDateTime(@NonNull String date, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyyMMdd HH:mm:ss";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Date 객체를 date 문자열로 변환하여 반환합니다. 반환되는 date 문자열은 yyyyMMdd 형식입니다.
     *
     * @param date 변환할 Date 객체
     * @return 변환된 date 문자열
     */
    public static String dateToString(@NonNull Date date) {
        return dateToString(date, DEFAULT_FORMAT);
    }

    /**
     * <pre>
     * Date 객체를 date 문자열로 변환하여 반환합니다.
     * format 인수를 통해 반환되는 date 문자열의 형식을 지정할 수 있습니다. format이 지정되지 않은 경우, 기본적으로 yyyyMMdd 형식이 사용됩니다.
     * </pre>
     *
     * @param date 변환할 Date 객체
     * @param format 반환될 date 문자열의 형식
     * @return 변환된 date 문자열
     */
    public static String dateToString(@NonNull Date date, @NonNull String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * LocalDate 객체를 date 문자열로 변환하여 반환합니다. 반환되는 date 문자열은 yyyyMMdd 형식입니다.
     *
     * @param date 변환할 LocalDate 객체
     * @return 변환된 date 문자열
     */
    public static String dateToString(@NonNull LocalDate date) {
        return dateToString(date, DEFAULT_FORMAT);
    }

    /**
     * <pre>
     * LocalDate 객체를 date 문자열로 변환하여 반환합니다.
     * format 인수를 통해 반환되는 date 문자열의 형식을 지정할 수 있습니다. format이 지정되지 않은 경우, 기본적으로 yyyyMMdd 형식이 사용됩니다.
     * </pre>
     *
     * @param date 변환할 LocalDate 객체
     * @param format 반환될 date 문자열의 형식
     * @return 변환된 date 문자열
     */
    public static String dateToString(@NonNull LocalDate date, @NonNull String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    /**
     * LocalDateTime 객체를 date 문자열로 변환하여 반환합니다. 반환되는 date 문자열은 yyyyMMdd HH:mm:ss 형식입니다.
     *
     * @param date 변환할 LocalDateTime 객체
     * @return 변환된 date 문자열
     */
    public static String dateToString(@NonNull LocalDateTime date) {
        return dateToString(date, null);
    }

    /**
     * <pre>
     * LocalDateTime 객체를 date 문자열로 변환하여 반환합니다.
     * format 인수를 통해 반환되는 date 문자열의 형식을 지정할 수 있습니다. format이 지정되지 않은 경우, 기본적으로 yyyyMMdd HH:mm:ss 형식이 사용됩니다.
     * </pre>
     *
     * @param date 변환할 LocalDateTime 객체
     * @param format 반환될 date 문자열의 형식
     * @return 변환된 date 문자열
     */
    public static String dateToString(@NonNull LocalDateTime date, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyyMMdd HH:mm:ss";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    /**
     * Date 객체가 null인지 여부를 반환합니다.
     *
     * @param date null 여부를 확인할 Date 객체
     * @return date가 null인 경우 true, 그렇지 않은 경우 false
     */
    public static boolean isNull(Date date) {
        return date == null;
    }

    // 문자열이 유효한 연도인지 여부를 반환합니다.
    private static boolean isValidYear(@NonNull String year) {
        try {
            int y = Integer.parseInt(year);
            return isValidYear(y);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // 정수가 유효한 연도인지 여부를 반환합니다.
    private static boolean isValidYear(int year) {
        return year >= 1 && year <= 9999;
    }

    // 문자열이 유효한 월인지 여부를 반환합니다.
    private static boolean isValidMonth(@NonNull String month) {
        try {
            int m = Integer.parseInt(month);
            return isValidMonth(m);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // 정수가 유효한 월인지 여부를 반환합니다.
    private static boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }

    // 정수가 유효한 일인지 여부를 반환합니다.
    private static boolean isValidDay(int year, int month, int day) {
        return day >= 1 && day <= getLastDay(year, month);
    }
}
