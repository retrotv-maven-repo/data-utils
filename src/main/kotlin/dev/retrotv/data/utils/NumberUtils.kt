@file:JvmName("NumberUtils")
package dev.retrotv.data.utils

import java.util.*

const val ARGUMENTS_CANT_LESS_THEN_TWO = "인자의 개수는 2보다 작을 수 없습니다."

/**
 * 가변 인자로 입력받은 Long 자료형 값들의 최소공배수를 구하고 반환합니다.
 *
 * @param values 최소공배수를 구할 값들
 * @return 최소공배수 값
 */
fun leastCommonMultiple(vararg values: Long): Long {
    require(values.size >= 2) { ARGUMENTS_CANT_LESS_THEN_TWO }

    val queue: Queue<Long> = LinkedList(values.toList())
    var lcmValue: Long? = null

    while (queue.peek() != null) {
        val val1 = lcmValue ?: queue.poll()
        val val2 = queue.poll()
        lcmValue = val1?.let { lcm(it, val2) }
    }

    return lcmValue!!
}

/**
 * 가변 인자로 입력받은 Int 자료형 값들의 최소공배수를 구하고 반환합니다.
 *
 * @param values 최소공배수를 구할 값들
 * @return 최소공배수 값
 */
fun leastCommonMultiple(vararg values: Int): Int {
    require(values.size >= 2) { ARGUMENTS_CANT_LESS_THEN_TWO }

    val queue: Queue<Int> = LinkedList(values.toList())
    var lcmValue: Int? = null

    while (queue.peek() != null) {
        val val1 = lcmValue ?: queue.poll()
        val val2 = queue.poll()
        lcmValue = val1?.let { lcm(it, val2) }
    }

    return lcmValue!!
}

private fun lcm(value1: Long, value2: Long): Long = (value1 * value2) / gcm(value1, value2)
private fun lcm(value1: Int, value2: Int): Int = (value1 * value2) / gcm(value1, value2)

/**
 * 가변 인자로 입력받은 Long 자료형 값들의 최대공약수를 구하고 반환합니다.
 *
 * @param values 최대공약수를 구할 값들
 * @return 최대공약수 값
 */
fun greatestCommonDivisor(vararg values: Long): Long {
    require(values.size >= 2) { ARGUMENTS_CANT_LESS_THEN_TWO }

    val queue: Queue<Long> = LinkedList(values.toList())
    var gcmValue: Long? = null

    while (queue.peek() != null) {
        val val1 = gcmValue ?: queue.poll()
        val val2 = queue.poll()
        gcmValue = val1?.let { gcm(it, val2) }
    }

    return gcmValue!!
}

/**
 * 가변 인자로 입력받은 Int 자료형 값들의 최대공약수를 구하고 반환합니다.
 *
 * @param values 최대공약수를 구할 값들
 * @return 최대공약수 값
 */
fun greatestCommonDivisor(vararg values: Int): Int {
    require(values.size >= 2) { ARGUMENTS_CANT_LESS_THEN_TWO }

    val queue: Queue<Int> = LinkedList(values.toList())
    var gcmValue: Int? = null

    while (queue.peek() != null) {
        val val1 = gcmValue ?: queue.poll()
        val val2 = queue.poll()
        gcmValue = val1?.let { gcm(it, val2) }
    }

    return gcmValue!!
}

private fun gcm(value1: Long, value2: Long): Long =
    if (value2 != 0.toLong()) {
        gcm(value2, value1 % value2)
    } else {
        value1
    }

private fun gcm(value1: Int, value2: Int): Int =
    if (value2 != 0) {
        gcm(value2, value1 % value2)
    } else {
        value1
    }