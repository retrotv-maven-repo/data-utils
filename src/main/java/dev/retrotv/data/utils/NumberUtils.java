package dev.retrotv.data.utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 숫자 관련 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 숫자와 관련된 다양한 유틸리티 메서드를 제공합니다.
 * 최소공배수(LCM)와 최대공약수(GCD)를 계산하는 메서드, 객체가 null인지 확인하는 메서드 등을 포함합니다.
 * </p>
 *
 * @author  yjj8353
 * @since   1.0.0
 */
@SuppressWarnings({"java:S2259", "DataFlowIssue"}) // 데이터 흐름 상 queue와 lcmValue가 null이 될 수 없으므로, 해당 경고는 무시함
public final class NumberUtils {
    private static final String ARGUMENTS_CANT_LESS_THEN_TWO = "인자의 개수는 2보다 작을 수 없습니다.";

    private NumberUtils() {
        throw new UnsupportedOperationException("NumberUtils는 인스턴스화 할 수 없습니다.");
    }

    /**
     * 가변 인자로 입력받은 Long 자료형 값들의 최소공배수를 구하고 반환합니다.
     *
     * @param values 최소공배수를 구할 값들
     * @return 최소공배수 값
     */
    public static long leastCommonMultiple(long... values) {
        if (values.length < 2) throw new IllegalArgumentException(ARGUMENTS_CANT_LESS_THEN_TWO);

        Queue<Long> queue = new LinkedList<>();
        for (long v : values) queue.add(v);
        Long lcmValue = null;

        while (!queue.isEmpty()) {
            long val1 = lcmValue != null ? lcmValue : queue.poll();
            long val2 = queue.poll();
            lcmValue = lcm(val1, val2);
        }

        return lcmValue;
    }

    /**
     * 가변 인자로 입력받은 Int 자료형 값들의 최소공배수를 구하고 반환합니다.
     *
     * @param values 최소공배수를 구할 값들
     * @return 최소공배수 값
     */
    public static int leastCommonMultiple(int... values) {
        if (values.length < 2) throw new IllegalArgumentException(ARGUMENTS_CANT_LESS_THEN_TWO);

        Queue<Integer> queue = new LinkedList<>();
        for (int v : values) queue.add(v);
        Integer lcmValue = null;

        while (!queue.isEmpty()) {
            int val1 = lcmValue != null ? lcmValue : queue.poll();
            int val2 = queue.poll();
            lcmValue = lcm(val1, val2);
        }

        return lcmValue;
    }

    /**
     * 가변 인자로 입력받은 Long 자료형 값들의 최대공약수를 구하고 반환합니다.
     *
     * @param values 최대공약수를 구할 값들
     * @return 최대공약수 값
     */
    public static long greatestCommonDivisor(long... values) {
        if (values.length < 2) throw new IllegalArgumentException(ARGUMENTS_CANT_LESS_THEN_TWO);

        Queue<Long> queue = new LinkedList<>();
        for (long v : values) queue.add(v);
        Long gcmValue = null;

        while (!queue.isEmpty()) {
            long val1 = gcmValue != null ? gcmValue : queue.poll();
            long val2 = queue.poll();
            gcmValue = gcm(val1, val2);
        }

        return gcmValue;
    }

    /**
     * 가변 인자로 입력받은 Int 자료형 값들의 최대공약수를 구하고 반환합니다.
     *
     * @param values 최대공약수를 구할 값들
     * @return 최대공약수 값
     */
    public static int greatestCommonDivisor(int... values) {
        if (values.length < 2) throw new IllegalArgumentException(ARGUMENTS_CANT_LESS_THEN_TWO);

        Queue<Integer> queue = new LinkedList<>();
        for (int v : values) queue.add(v);
        Integer gcmValue = null;

        while (!queue.isEmpty()) {
            int val1 = gcmValue != null ? gcmValue : queue.poll();
            int val2 = queue.poll();
            gcmValue = gcm(val1, val2);
        }

        return gcmValue;
    }

    /**
     * 객체가 null인지 확인합니다.
     *
     * @param value null 여부를 확인할 Int 객체
     * @return null 여부
     */
    public static boolean isNull(Integer value) {
        return value == null;
    }

    /**
     * 객체가 null인지 확인합니다.
     *
     * @param value null 여부를 확인할 Long 객체
     * @return null 여부
     */
    public static boolean isNull(Long value) {
        return value == null;
    }

    /**
     * 객체가 null인지 확인합니다.
     *
     * @param value null 여부를 확인할 Double 객체
     * @return null 여부
     */
    public static boolean isNull(Double value) {
        return value == null;
    }

    // 내부 계산 메서드
    private static long lcm(long value1, long value2) {
        return (value1 * value2) / gcm(value1, value2);
    }

    private static int lcm(int value1, int value2) {
        return (value1 * value2) / gcm(value1, value2);
    }

    private static long gcm(long value1, long value2) {
        return value2 != 0 ? gcm(value2, value1 % value2) : value1;
    }

    private static int gcm(int value1, int value2) {
        return value2 != 0 ? gcm(value2, value1 % value2) : value1;
    }
}