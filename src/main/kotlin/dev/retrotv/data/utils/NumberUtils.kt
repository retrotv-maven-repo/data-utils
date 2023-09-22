@file:JvmName("NumberUtils")
package dev.retrotv.data.utils

import java.util.LinkedList
import java.util.Queue

fun leastCommonMultiple(vararg values: Long): Long {
    require(values.size >= 2) { "매개변수의 개수는 2보다 작을 수 없습니다." }
    val queue: Queue<Long> = LinkedList(values.toList())

    var lcmValue: Long? = null
    while(queue.peek() != null) {
        val val1 = lcmValue ?: queue.poll()
        val val2 = queue.poll()
        lcmValue = val1?.let { lcm(it, val2) }
    }

    return lcmValue!!
}

private fun lcm(value1: Long, value2: Long): Long = (value1 * value2) / gcm(value1, value2)

fun greatestCommonDivisor(vararg values: Long): Long {
    require(values.size >= 2) { "매개변수의 개수는 2보다 작을 수 없습니다." }
    val queue: Queue<Long> = LinkedList(values.toList())

    var gcmValue: Long? = null
    while(queue.peek() != null) {
        val val1 = gcmValue ?: queue.poll()
        val val2 = queue.poll()
        gcmValue = val1?.let { gcm(it, val2) }
    }

    return gcmValue!!
}

private fun gcm(value1: Long, value2: Long): Long = if(value2 != 0.toLong()) gcm(value2, value1 % value2) else value1